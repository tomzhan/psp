package com.third.fpaltform.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.third.fpaltform.common.MediaTypes;
import com.third.fpaltform.entity.PartModelEntity;
import com.third.fpaltform.exception.RestException;
import com.third.fpaltform.service.PartModelService;

@RestController
@RequestMapping(value = "/rest")
public class PartModelManager 
{
	@Resource
	private PartModelService partModelService;

	/**
	 * 新增部件类型信息
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/partModel", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public Result insertPartModel(@RequestBody Map<String, Object> input) 
	{
		if ( !input.containsKey("type") || !input.containsKey("modelId")  
				|| !input.containsKey("manufactor") || !input.containsKey("meno") || !input.containsKey("name")) {
			throw new RestException("000001");
		}
		
		Object o1 = input.get("type");
		Object o2 = input.get("modelId");
		Object o3 = input.get("manufactor");
		Object o4 = input.get("meno");
		Object o5 = input.get("name");
		
		if ( ! (o1 instanceof Integer) || ! (o2 instanceof String)
				|| ! (o3 instanceof String) || ! (o4 instanceof String) || ! (o5 instanceof String)) {
			throw new RestException("000002");
		}
		
		Integer type = (Integer) o1;
		String modelId = (String) o2;
		String manufactor = (String) o3;
		String meno = (String) o4;
		String name = (String) o5;
		
		PartModelEntity partModel = partModelService.insertPartModel(type, modelId, manufactor, meno, name);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setContent(partModel);
		result.setInfo("新增部件类型成功");
		return result;
	}
	
	/**
	 * 修改部件类型信息
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/partModel", method = RequestMethod.PUT, produces = MediaTypes.JSON_UTF_8)
	public Result updatePartModel(@RequestBody Map<String, Object> input) 
	{
		if ( !input.containsKey("uid")) {
			throw new RestException("000001");
		}
		
		if ( !(input.get("uid") instanceof Integer)) {
			throw new RestException("000002");
		}
		
		Integer partModelUid = (Integer) input.get("uid");
		Integer type = null;
		String modelId = null;
		String manufactor = null;
		String meno = null;
		String name = null;
		
		if(input.containsKey("type") && (input.get("type") instanceof Integer))
		{
			type = (Integer)input.get("type");
		}
		
		if(input.containsKey("modelId") && (input.get("modelId") instanceof String))
		{
			modelId = (String)input.get("modelId");
		}
		
		if(input.containsKey("manufactor") && (input.get("manufactor") instanceof String))
		{
			manufactor = (String)input.get("manufactor");
		}
		
		if(input.containsKey("meno") && (input.get("meno") instanceof String))
		{
			meno = (String)input.get("meno");
		}
		
		if(input.containsKey("name") && (input.get("name") instanceof String))
		{
			name = (String)input.get("name");
		}
		
		partModelService.updatePartModel(partModelUid, type, modelId, manufactor, meno, name);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setInfo("修改部件类型信息成功");
		return result;
	}
	
	/**
	 * 删除部件类型信息
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/partModel/{uid}", method = RequestMethod.DELETE, produces = MediaTypes.JSON_UTF_8)
	public Result deletePartModel(@PathVariable("uid") Integer uid) 
	{
		
		partModelService.deletePartModel(uid);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setInfo("删除部件信息成功");
		return result;
	}
	
	/**
	 * 查询部件类型信息
	 * @param name
	 * @param maufactory
	 * @param model
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/partModels", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Result findPartModels(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "maufactory", required = false) String maufactory, 
			@RequestParam(value = "model", required = false) String model, 
			@RequestParam(value = "pageNumber", required = true) Integer pageNumber, 
			@RequestParam(value = "limit", required = true) Integer limit){
		if(null == pageNumber || null == limit){
			throw new RestException("000001");
		}
		Page<PartModelEntity> partModels = partModelService.findPartModels(name, maufactory, model, pageNumber, limit);
		List<PartModelEntity> partModelList = partModels.getContent();
		Integer totalElements = (int) partModels.getTotalElements();
		
		Map<String,Object> contentMap = new HashMap<String,Object>();
		contentMap.put("partModels", partModelList);
		contentMap.put("totalElements", totalElements);
		
		Result result = new Result();
		result.setStatus(result.STATUS_SUCCESS);
		result.setInfo("部件类型信息查询成功");
		Gson json = new Gson();
		result.setContent(json.toJson(contentMap));	
		return result;
	}
	
}
