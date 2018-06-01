package com.third.fpaltform.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.third.fpaltform.entity.BatteryDiagnosis;
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
	
	
	public VehicleEntity findDiagnosis(String vin) 
	{
		VehicleEntity v = vehicleRepository.findByVin(vin);
		
		return v;
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
	public void vehicleBind(Diagnosis diagnosis) 
	{
		String vin = diagnosis.getVin() == null ? null : diagnosis.getVin();
		BatteryDiagnosis batteryDiagnosis = diagnosis.getBatteryDiagnosis();
		DriveDiagnosis driveDiagnosis = diagnosis.getDriveDiagnosis();
		InstrumentDiagnosis instrumentDiagnosis = diagnosis.getInstrumentDiagnosis();
		
		VehicleEntity v = vehicleRepository.findByVin(vin);
		
		PartEntity part1 = partRepository.findByBarCode(batteryDiagnosis.getBarcode());
		if(part1 == null)
		{
			part1 = new PartEntity();
			part1.setBarCode(batteryDiagnosis.getBarcode());
			
			part1 = partRepository.save(part1);
		}
		
		PartEntity part2 = partRepository.findByBarCode(driveDiagnosis.getBarcode());
		if(part2 == null)
		{
			part2 = new PartEntity();
			part2.setBarCode(driveDiagnosis.getBarcode());
			
			part2 = partRepository.save(part2);
		}
		
		PartEntity part3 = partRepository.findByBarCode(instrumentDiagnosis.getBarcode());
		
		if(part3 == null)
		{
			part3 = new PartEntity();
			part3.setBarCode(instrumentDiagnosis.getBarcode());
			
			part3 = partRepository.save(part3);
		}
		
		if(v == null)
		{
			v = new VehicleEntity();
			v.setVin(vin);
			v.setBatteryDiagnosis(new Gson().toJson(batteryDiagnosis));
			v.setDriveDiagnosis(new Gson().toJson(driveDiagnosis));
			v.setInstrumentDiagnosis(new Gson().toJson(instrumentDiagnosis));
			v.setBatteryId(part1.getUid());
			v.setDriverId(part2.getUid());
			v.setInstrumentId(part3.getUid());
			v.setCreateTime(new Date());
		}else
		{
			v.setBatteryDiagnosis(new Gson().toJson(batteryDiagnosis));
			v.setDriveDiagnosis(new Gson().toJson(driveDiagnosis));
			v.setInstrumentDiagnosis(new Gson().toJson(instrumentDiagnosis));
			v.setBatteryId(part1.getUid());
			v.setDriverId(part2.getUid());
			v.setInstrumentId(part3.getUid());
			v.setUpdateTime(new Date());
		}
	}
	
}
