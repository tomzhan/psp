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
import com.third.fpaltform.entity.StoreInfoEntity;
import com.third.fpaltform.exception.RestException;
import com.third.fpaltform.service.StoreInfoService;

@RestController
@RequestMapping(value = "/rest")
public class StoreInfoManager 
{
	
	@Resource
	private StoreInfoService storeInfoService;

	/**
	 * 新增门店信息
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/storeInfo", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public Result insertStoreInfo(@RequestBody Map<String, Object> input) 
	{
		if ( !input.containsKey("name") || !input.containsKey("nation") || !input.containsKey("unit") 
				|| !input.containsKey("city") || !input.containsKey("addr") || !input.containsKey("contact")
				 || !input.containsKey("email") || !input.containsKey("phone") || !input.containsKey("mobile")
				 || !input.containsKey("size") || !input.containsKey("divisionUid")) {
			throw new RestException("000001");
		}
		
		Object o1 = input.get("name");
		Object o2 = input.get("nation");
		Object o3 = input.get("unit");
		Object o4 = input.get("city");
		Object o5 = input.get("addr");
		Object o6 = input.get("contact");
		Object o7 = input.get("email");
		Object o8 = input.get("phone");
		Object o9 = input.get("mobile");
		Object o10 = input.get("size");
		Object o11 = input.get("divisionUid");
		
		if ( ! (o1 instanceof String) || ! (o2 instanceof String) || ! (o3 instanceof String) 
				|| ! (o4 instanceof String) || ! (o5 instanceof String) || ! (o6 instanceof String)
				 || ! (o7 instanceof String) || ! (o8 instanceof String) || ! (o9 instanceof String)
				 || ! (o10 instanceof Integer) || ! (o11 instanceof Integer)) {
			throw new RestException("000002");
		}
		
		String name = (String) o1;
		String nation = (String) o2;
		String unit = (String) o3;
		String city = (String) o4;
		String addr = (String) o5;
		String contact = (String) o6;
		String email = (String) o7;
		String phone = (String) o8;
		String mobile = (String) o9;
		Integer size = (Integer) o10;
		Integer divisionUid = (Integer) o11;
		
		StoreInfoEntity storeInfo = storeInfoService.insertStoreInfo(name, nation, unit, city, addr,contact,email,phone,mobile,size,divisionUid);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setContent(storeInfo);
		result.setInfo("新增门店信息成功");
		return result;
	}
	
	/**
	 * 修改门店信息
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/storeInfo", method = RequestMethod.PUT, produces = MediaTypes.JSON_UTF_8)
	public Result updateStoreInfo(@RequestBody Map<String, Object> input) 
	{
		if ( !input.containsKey("uid")) {
			throw new RestException("000001");
		}
		
		if ( !(input.get("uid") instanceof Integer)) {
			throw new RestException("000002");
		}
		
		Integer uid = (Integer) input.get("uid");
		String name = null;
		String nation = null;
		String unit = null;
		String city = null;
		String addr = null;
		String contact = null;
		String email = null;
		String phone = null;
		String mobile = null;
		Integer size = null;
		
		if(input.containsKey("name") && (input.get("name") instanceof String))
		{
			name = (String)input.get("name");
		}
		
		if(input.containsKey("nation") && (input.get("nation") instanceof String))
		{
			nation = (String)input.get("nation");
		}
		
		if(input.containsKey("unit") && (input.get("unit") instanceof String))
		{
			unit = (String)input.get("unit");
		}
		
		if(input.containsKey("city") && (input.get("city") instanceof String))
		{
			city = (String)input.get("city");
		}
		
		if(input.containsKey("contact") && (input.get("contact") instanceof String))
		{
			contact = (String)input.get("contact");
		}
		
		if(input.containsKey("email") && (input.get("email") instanceof String))
		{
			email = (String)input.get("email");
		}
		
		if(input.containsKey("phone") && (input.get("phone") instanceof String))
		{
			phone = (String)input.get("phone");
		}
		
		if(input.containsKey("mobile") && (input.get("mobile") instanceof String))
		{
			mobile = (String)input.get("mobile");
		}
		
		if(input.containsKey("size") && (input.get("size") instanceof Integer))
		{
			size = (Integer)input.get("size");
		}
		
		storeInfoService.updateStoreInfo(uid, name, nation, unit, city, addr,contact,email,phone,mobile,size);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setInfo("门店信息更新成功");
		return result;
	}
	
	/**
	 * 删除门店信息
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/storeInfo/{uid}", method = RequestMethod.DELETE, produces = MediaTypes.JSON_UTF_8)
	public Result deleteStoreInfo(@PathVariable("uid") Integer uid) 
	{
		storeInfoService.deleteStoreInfo(uid);
		
		Result result = new Result();
		result.setStatus(Result.STATUS_SUCCESS);
		result.setCode("");
		result.setInfo("门店信息删除成功");
		return result;
	}
	
	
	/**
	 * 查询门店信息成功
	 * @param name
	 * @param maufactory
	 * @param model
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/storeInfos", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public Result findStoreInfos(@RequestParam(value = "nation", required = false) String nation,
			@RequestParam(value = "city", required = false) String city, 
			@RequestParam(value = "pageNumber", required = true) Integer pageNumber, 
			@RequestParam(value = "limit", required = true) Integer limit){
		if(null == pageNumber || null == limit){
			throw new RestException("000001");
		}
		Page<StoreInfoEntity> storeInfos = storeInfoService.findStoreInfos(nation, city, pageNumber, limit);
		List<StoreInfoEntity> partModelList = storeInfos.getContent();
		Integer totalElements = (int) storeInfos.getTotalElements();
		
		Map<String,Object> contentMap = new HashMap<String,Object>();
		contentMap.put("storeInfos", partModelList);
		contentMap.put("totalElements", totalElements);
		
		Result result = new Result();
		result.setStatus(result.STATUS_SUCCESS);
		result.setInfo("查询门店信息成功成功");
		Gson json = new Gson();
		result.setContent(json.toJson(contentMap));	
		return result;
	}
}
