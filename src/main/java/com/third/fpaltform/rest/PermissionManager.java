package com.third.fpaltform.rest;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.third.fpaltform.common.MediaTypes;
import com.third.fpaltform.service.PermissionService;

@RestController
@RequestMapping(value = "/rest")
public class PermissionManager {
	
	private static Logger logger = LoggerFactory.getLogger(PermissionManager.class);
	
	@Autowired
	PermissionService permissionService;
	
	
	/**
	 * 新增权限
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/permission", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public  ResponseEntity<String> insertPermission(@RequestBody Map<String, Object> input,@RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();

		if ( !input.containsKey("permissionName") || !input.containsKey("permissionIdentify")) {
			result.setStatus(0);
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("permission insert:: " + result.getInfo());
			return entity;
		}
		
		if ( ! (input.get("permissionName") instanceof String) && ! (input.get("permissionIdentify") instanceof String)) {
			
			result.setStatus(0);
			result.setInfo("参数类型错误");
			
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("permission insert:: " + result.getInfo());
			return entity;
		}
		
		
		String permissionName = (String) input.get("permissionName");
		String permissionIdentify = (String) input.get("permissionIdentify");
		String remark = null;
		
		
		if(input.containsKey("remark") && (input.get("remark") instanceof String))
		{
			remark = (String) input.get("roleName");
		}
		
		result = permissionService.insertPermission(permissionName, permissionIdentify, remark);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("permission insert:: successfully");
		} else {
			logger.error("permission insert:: failed Err: " + result.getInfo());
		}
		return entity;
	}
	
	/**
	 * 编辑权限
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/permission", method = RequestMethod.PUT, produces = MediaTypes.JSON_UTF_8)
	public  ResponseEntity<String> updatePermission(@RequestBody Map<String, Object> input,@RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		if ( !input.containsKey("uid") || !input.containsKey("permissionName") || !input.containsKey("permissionIdentify")) {
			result.setStatus(0);
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("permission update:: " + result.getInfo());
			return entity;
		}
		
		
		if ( ! (input.get("uid") instanceof Integer)) {
			
			result.setStatus(0);
			result.setInfo("参数类型错误");
			
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("permission update:: " + result.getInfo());
			return entity;
		}
		
		Integer uid = (Integer) input.get("uid");
		String permissionName = null;
		String permissionIdentify = null;
		String remark = null;
		
		if(input.containsKey("permissionName") && (input.get("permissionName") instanceof String))
		{
			permissionName = (String) input.get("permissionName");
		}
		
		if(input.containsKey("permissionIdentify") && (input.get("permissionIdentify") instanceof String))
		{
			permissionIdentify = (String) input.get("permissionIdentify");
		}
		
		if(input.containsKey("remark") && (input.get("remark") instanceof String))
		{
			remark = (String) input.get("remark");
		}
		
		
		result = permissionService.updatePermission(uid, permissionName, permissionIdentify, remark);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("permission update:: successfully");
		} else {
			logger.error("permission update:: failed Err: " + result.getInfo());
		}
		return entity;
	}
	
	
	/**
	 * 删除权限
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/permission/{uid}", method = RequestMethod.DELETE, produces = MediaTypes.JSON_UTF_8)
	public  ResponseEntity<String> deletePermission(@PathVariable("uid") Integer uid, @RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		if ( uid == null ) {
			result.setStatus(0);
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("permission delete:: " + result.getInfo());
			return entity;
		}
		
		result = permissionService.deletePermission(uid);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("permission delete:: successfully");
		} else {
			logger.error("permission delete:: failed Err: " + result.getInfo());
		}
		return entity;
		
	}
	
	/**
	 * 查询所有权限信息
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/permissions", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public  ResponseEntity<String> getPermissions(@RequestParam(value="pageNumber", required=true) Integer pageNumber, 
			@RequestParam(value="limit", required=true) Integer limit,
			@RequestParam(value="keyword", required=false) String keyword, 
			@RequestParam(value="sortField", required=false) String sortField,
			@RequestParam(value="sortType", required=false) String sortType, @RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		result = permissionService.getPermissions(pageNumber, limit, sortField, sortType, keyword);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("find permissions:: successfully");
		} else {
			logger.error("find permissions:: failed Err: " + result.getInfo());
		}
		return entity;
		
	}

}
