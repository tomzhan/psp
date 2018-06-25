/**
 * 
 */
package com.third.fpaltform.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.third.fpaltform.common.MediaTypes;
import com.third.fpaltform.entity.Diagnosis;
import com.third.fpaltform.entity.DiagnosisLog;
import com.third.fpaltform.entity.VehicleEntity;
import com.third.fpaltform.exception.RestException;
import com.third.fpaltform.service.DiagnosisService;

@RestController
@RequestMapping(value = "/rest")
public class DiagnosisManager 
{
	
	private static Logger logger = LoggerFactory.getLogger(DiagnosisManager.class);
	
	@Resource
	private DiagnosisService diagnosisService;
	
	/**
	 * 上报检测数据
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/upload/diagnosis", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public Result uploadDiagnosis(@RequestBody Diagnosis diagnosis) 
	{
		logger.info("upload diagnosis param :: " + new Gson().toJson(diagnosis));
		
		String vin = diagnosis.getVin() == null ? null : diagnosis.getVin();
		
		diagnosisService.saveVehicleDiagnosis(vin, diagnosis);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setInfo("上报检测数据成功");
		logger.info("上报检测数据成功");
		return result;
	}
	
	
	/**
	 * 查询当前车辆的检测数据
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/find/diagnosis", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Result findDiagnosis(@RequestParam(value="vin", required=false) String vin,
			@RequestParam(value="batteryId", required=false) String batteryId,
			@RequestParam(value="driverId", required=false) String driverId,
			@RequestParam(value="instrumentId", required=false) String instrumentId) 
	{
		List<VehicleEntity> vehicles = diagnosisService.findDiagnosis(vin, batteryId, driverId, instrumentId);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setContent(vehicles);
		result.setInfo("查询当前车辆的检测数据成功");
		logger.info("查询当前车辆的检测数据成功");
		return result;
	}
	
	
	/**
	 * 查询车辆的历史检测
	 * @param vin
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/find/history/diagnosis", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Result findHistoryDiagnosis(@RequestParam(value="vin", required=true) String vin,
			@RequestParam(value="barcode", required=false) String barcode,
			@RequestParam(value="batteryId", required=false) String batteryId,
			@RequestParam(value="driverId", required=false) String driverId,
			@RequestParam(value="instrumentId", required=false) String instrumentId,
			@RequestParam(value="pageNumber", required=true) Integer pageNumber, 
			@RequestParam(value="limit", required=true) Integer limit) 
	{
		if(vin == null)
		{
			throw new RestException("500000");
		}
		
		Page<DiagnosisLog> userPage = diagnosisService.findHistoryDiagnosis(vin, barcode, batteryId, driverId, instrumentId, pageNumber, limit);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("diagnosisLogs", userPage.getContent());
		map.put("totalPages", userPage.getTotalPages());
		map.put("totalElements", userPage.getTotalElements());
		map.put("numberOfCurrentPage", userPage.getNumberOfElements());
		map.put("limit", userPage.getSize());
		map.put("pageNumber", userPage.getNumber());
		map.put("isFirstPage", userPage.isFirst());
		map.put("isLastPage", userPage.isLast());
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setContent(map);
		result.setInfo("查询车辆的历史检测成功");
		logger.info("查询车辆的历史检测成功");
		return result;
		
	}
	
	/**
	 * 车辆绑定
	 * @param diagnosis
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/vehicle/bind", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public Result vehicleBind(@RequestBody Diagnosis diagnosis) 
	{
		diagnosisService.vehicleBind(diagnosis);
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setInfo("车辆绑定成功");
		logger.info("车辆绑定成功");
		return result;
	}
	
	/**
	 * 解除绑定
	 * @param vin
	 * @param batteryId
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/vehicle/unBind", method = RequestMethod.PUT, produces = MediaTypes.JSON_UTF_8)
	public Result vehicleUnBind(@RequestParam(value="vin", required=true) String vin,
			@RequestParam(value="barCode", required=true) String barCode,
			@RequestParam(value="type", required=true) Integer type) 
	{
		diagnosisService.vehicleUnBind(vin, barCode, type);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setInfo("车辆解绑成功");
		logger.info("车辆解绑成功");
		return result;
	}

}
