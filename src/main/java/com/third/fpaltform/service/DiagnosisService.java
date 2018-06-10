package com.third.fpaltform.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.third.fpaltform.entity.BatteryDiagnosis;
import com.third.fpaltform.entity.DebugParamEntity;
import com.third.fpaltform.entity.Diagnosis;
import com.third.fpaltform.entity.DiagnosisLog;
import com.third.fpaltform.entity.DriveDiagnosis;
import com.third.fpaltform.entity.InstrumentDiagnosis;
import com.third.fpaltform.entity.PartEntity;
import com.third.fpaltform.entity.VehicleEntity;
import com.third.fpaltform.exception.ServiceException;
import com.third.fpaltform.repository.DiagnosisLogRepository;
import com.third.fpaltform.repository.PartRepository;
import com.third.fpaltform.repository.VehicleRepository;

@Component
public class DiagnosisService 
{
	@Resource
	private VehicleRepository vehicleRepository;
	
	@Resource
	private DiagnosisLogRepository diagnosisLogRepository;
	
	@Resource
	private PartRepository partRepository;
	
	/**
	 * 上报检测数据
	 * @param vin
	 * @param batteryDiagnosis
	 * @param driveDiagnosis
	 * @param instrumentDiagnosis
	 */
	@Transactional
	public void saveVehicleDiagnosis(String vin, String batteryDiagnosis, String driveDiagnosis, String instrumentDiagnosis) 
	{
		
		VehicleEntity v = vehicleRepository.findByVin(vin);
		
		if(v == null)
		{
			v = new VehicleEntity();
			v.setVin(vin);
			v.setBatteryDiagnosis(batteryDiagnosis);
			v.setDriveDiagnosis(driveDiagnosis);
			v.setInstrumentDiagnosis(instrumentDiagnosis);
			v.setCreateTime(new Date());
		}else
		{
			v.setBatteryDiagnosis(batteryDiagnosis);
			v.setDriveDiagnosis(driveDiagnosis);
			v.setInstrumentDiagnosis(instrumentDiagnosis);
			v.setUpdateTime(new Date());
		}
		
		v = vehicleRepository.save(v);//保存车辆数据
		
		DiagnosisLog diagnosis = new DiagnosisLog();
		diagnosis.setBatteryDiagnosis(batteryDiagnosis);
		diagnosis.setDriveDiagnosis(driveDiagnosis);
		diagnosis.setInstrumentDiagnosis(instrumentDiagnosis);
		diagnosis.setVehicle(v);
		diagnosis.setCreateTime(new Date());
		
		diagnosisLogRepository.save(diagnosis);
	}
	
	
	public List<VehicleEntity> findDiagnosis(String vin, String batteryId, String driverId, String instrumentId) 
	{
		List<VehicleEntity> vehicles = vehicleRepository.findAll(vehicleSpec(vin, batteryId, driverId, instrumentId));
		return vehicles;
	}
	
	
	private Specification<VehicleEntity> vehicleSpec(final String vin, final String batteryId, final String driverId, final String instrumentId) 
	{
		return new Specification<VehicleEntity>(){

			@Override
			public Predicate toPredicate(Root<VehicleEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<Predicate>(); 
				if(!"".equals(vin) && null != vin)
				{
					predicates.add(cb.equal(root.<String>get("vin"), vin));
				}	
				if(!"".equals(batteryId) && null != batteryId)
				{
					predicates.add(cb.equal(root.<String>get("batteryId"), batteryId));
				}
				if(!"".equals(driverId) && null != driverId)
				{
					predicates.add(cb.equal(root.<String>get("driverId"), driverId));
				}
				if(!"".equals(instrumentId) && null != instrumentId)
				{
					predicates.add(cb.equal(root.<String>get("instrumentId"), instrumentId));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
	}
	

	
	/**
	 * 查询当前车辆的检测数据
	 * @param vin
	 */
	public Page<DiagnosisLog> findHistoryDiagnosis(String vin, Integer pageNumber, Integer limit) 
	{
		VehicleEntity v = vehicleRepository.findByVin(vin);
		
		if(v == null)
		{
			throw new ServiceException("500001");
		}
		
		Page<DiagnosisLog> userPage = diagnosisLogRepository.findByVehicleUid(v.getUid(), new PageRequest(pageNumber, limit, new Sort(Direction.DESC, "createTime")));
		
		return userPage;
	}

	/**
	 * 车辆绑定
	 * @param diagnosis
	 */
	@Transactional
	public void vehicleBind(Diagnosis diagnosis) 
	{
		String vin = diagnosis.getVin() == null ? null : diagnosis.getVin();
		BatteryDiagnosis batteryDiagnosis = diagnosis.getBatteryDiagnosis();
		DriveDiagnosis driveDiagnosis = diagnosis.getDriveDiagnosis();
		InstrumentDiagnosis instrumentDiagnosis = diagnosis.getInstrumentDiagnosis();
		String sensorId = diagnosis.getSensorId() == null ? null : diagnosis.getSensorId();
		String motoId = diagnosis.getMotoId() == null ? null : diagnosis.getMotoId();
		
		VehicleEntity v = vehicleRepository.findByVin(vin);
		
		PartEntity part1 = partRepository.findPart(batteryDiagnosis.getBarcode(), 1);
		if(part1 == null)
		{
			part1 = new PartEntity();
			part1.setBarCode(batteryDiagnosis.getBarcode());
			part1.setType(1);
			part1.setStatus(2);
			
			part1 = partRepository.save(part1);
		}
		
		PartEntity part2 = partRepository.findPart(driveDiagnosis.getBarcode(), 2);
		if(part2 == null)
		{
			part2 = new PartEntity();
			part2.setBarCode(driveDiagnosis.getBarcode());
			part2.setType(2);
			part2.setStatus(2);
			
			part2 = partRepository.save(part2);
		}
		
		PartEntity part3 = partRepository.findPart(instrumentDiagnosis.getBarcode(), 3);
		
		if(part3 == null)
		{
			part3 = new PartEntity();
			part3.setBarCode(instrumentDiagnosis.getBarcode());
			part3.setType(3);
			part3.setStatus(2);
			
			part3 = partRepository.save(part3);
		}
		
		if(v == null)
		{
			v = new VehicleEntity();
			v.setVin(vin);
			v.setBatteryDiagnosis(new Gson().toJson(batteryDiagnosis));
			v.setDriveDiagnosis(new Gson().toJson(driveDiagnosis));
			v.setInstrumentDiagnosis(new Gson().toJson(instrumentDiagnosis));
			v.setBatteryId(batteryDiagnosis.getBarcode());
			v.setDriverId(driveDiagnosis.getBarcode());
			v.setInstrumentId(instrumentDiagnosis.getBarcode());
			v.setSensorId(sensorId);
			v.setMotoId(motoId);
			v.setCreateTime(new Date());
			vehicleRepository.save(v);
		}else
		{
			v.setBatteryDiagnosis(new Gson().toJson(batteryDiagnosis));
			v.setDriveDiagnosis(new Gson().toJson(driveDiagnosis));
			v.setInstrumentDiagnosis(new Gson().toJson(instrumentDiagnosis));
			v.setBatteryId(batteryDiagnosis.getBarcode());
			v.setDriverId(driveDiagnosis.getBarcode());
			v.setInstrumentId(instrumentDiagnosis.getBarcode());
			v.setSensorId(sensorId == null ? v.getSensorId() : sensorId);
			v.setMotoId(motoId == null ? v.getMotoId() : motoId);
			v.setUpdateTime(new Date());
			vehicleRepository.save(v);
		}
		
	}

	/**
	 * 解除绑定
	 * @param vin
	 * @param barCode
	 * @param type
	 */
	@Transactional
	public void vehicleUnBind(String vin, String barCode, Integer type) 
	{
		VehicleEntity v = vehicleRepository.findByVin(vin);
		
		if(v == null)
		{
			throw new ServiceException("500001");
		}
		
		PartEntity part = partRepository.findPart(barCode, type);
		
		if(part == null)
		{
			throw new ServiceException("部件信息不存在");
		}
		
		if(type == 1)
		{
			v.setBatteryDiagnosis(null);
			v.setBatteryId(null);
		}else if(type == 2)
		{
			v.setDriveDiagnosis(null);
			v.setDriverId(null);
		}else if(type == 3)
		{
			v.setInstrumentDiagnosis(null);
			v.setInstrumentId(null);
		}
		
		vehicleRepository.save(v);
		
		part.setStatus(1);//禁用
		
		partRepository.save(part);
	}
	
}
