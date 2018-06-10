package com.third.fpaltform.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.third.fpaltform.common.MediaTypes;
import com.third.fpaltform.entity.DebugParamEntity;
import com.third.fpaltform.entity.PartModelEntity;
import com.third.fpaltform.exception.RestException;
import com.third.fpaltform.service.DebugParamService;

@RestController
@RequestMapping(value = "/rest")
public class DebugParamManager 
{
	@Resource
	private DebugParamService debugParamService;
	
	
	/**
	 * 查询参数列表接口
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/debugParam", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Result findDebugParams(@RequestParam(value = "barcode", required = true) String barcode,
			@RequestParam(value = "type", required = true) Integer type)
	{
		
		List<DebugParamEntity> debugParams = debugParamService.findDebugParams(barcode, type);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setContent(debugParams);
		result.setInfo("查询参数列表成功");
		return result;
	}
	
	/**
	 * 获取参数
	 * @param barcode
	 * @param type
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/getDebugParam", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Result getDebugParam(@RequestParam(value = "barcode", required = true) String barcode,
			@RequestParam(value = "type", required = true) Integer type)
	{
		HashMap<Object, List<Object>> debugParams = debugParamService.getDebugParam(barcode, type);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setContent(debugParams);
		result.setInfo("查询参数列表成功");
		return result;
	}
	
	
	/**
	 * 获取参数及曲线图片接口
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/query/debugParams", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Result queryDebugParams(@RequestParam(value = "bikeType", required = false) String bikeType,
			@RequestParam(value = "rate", required = false) String rate, 
			@RequestParam(value = "radius", required = false) String radius,
			@RequestParam(value = "model", required = false) String model, 
			@RequestParam(value = "pageNumber", required = true) Integer pageNumber, 
			@RequestParam(value = "limit", required = true) Integer limit){
		if(null == pageNumber || null == limit){
			throw new RestException("000001");
		}
		Page<DebugParamEntity> debugParams = debugParamService.queryDebugParams(bikeType, rate, radius, model, pageNumber, limit);
		List<DebugParamEntity> partModelList = debugParams.getContent();
		Integer totalElements = (int) debugParams.getTotalElements();
		
		Map<String,Object> contentMap = new HashMap<String,Object>();
		contentMap.put("debugParams", partModelList);
		contentMap.put("totalElements", totalElements);
		
		Result result = new Result();
		result.setStatus(result.STATUS_SUCCESS);
		result.setInfo("获取参数及曲线图片接口成功");
		Gson json = new Gson();
		result.setContent(json.toJson(contentMap));	
		return result;
	}

}
