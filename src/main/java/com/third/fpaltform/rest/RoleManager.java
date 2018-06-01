package com.third.fpaltform.rest;

import java.util.List;
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
import com.third.fpaltform.service.RoleService;

@RestController
@RequestMapping(value = "/rest")
public class RoleManager {
	
	private static Logger logger = LoggerFactory.getLogger(RoleManager.class);
	
	@Autowired
	private RoleService roleServicel;
	/**
	 * 新增角色
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/role", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> insertRole(@RequestBody Map<String, Object> input,@RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();

		if ( !input.containsKey("roleName") ) {
			result.setStatus(0);
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("role insert:: " + result.getInfo());
			return entity;
		}
		
		if ( ! (input.get("roleName") instanceof String) ) {
			
			result.setStatus(0);
			result.setInfo("参数类型错误");
			
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("role insert:: " + result.getInfo());
			return entity;
		}
		
		String roleName = (String) input.get("roleName");
		String remark = null;
		
		if(input.containsKey("remark") && (input.get("remark") instanceof String))
		{
			remark = (String) input.get("remark");
		}
		
		result = roleServicel.insertRole(roleName, remark);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("role insert:: successfully");
		} else {
			logger.error("role insert:: failed Err: " + result.getInfo());
		}
		return entity;
	}
	
	/**
	 * 编辑角色信息
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/role", method = RequestMethod.PUT, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> updateRole(@RequestBody Map<String, Object> input,@RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		if ( !input.containsKey("uid")) {
			result.setStatus(0);
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("role update:: " + result.getInfo());
			return entity;
		}
		
		
		if ( !(input.get("uid") instanceof Integer)) {
			result.setStatus(0);
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("role update:: " + result.getInfo());
			return entity;
		}
		
		Integer uid = (Integer) input.get("uid");
		String roleName = null;
		String remark = null;
		
		
		if(input.containsKey("roleName") && (input.get("roleName") instanceof String))
		{
			roleName = (String) input.get("roleName");
		}
		
		if(input.containsKey("remark") && (input.get("remark") instanceof String))
		{
			remark = (String) input.get("remark");
		}
		
		result = roleServicel.updateRole(uid, roleName, remark);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("role update:: successfully");
		} else {
			logger.error("role update:: failed Err: " + result.getInfo());
		}
		return entity;
	}
	
	
	/**
	 * 删除角色
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/role/{uid}", method = RequestMethod.DELETE, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> deleteRole(@PathVariable("uid") Integer uid, @RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		if ( uid == null) {
			result.setStatus(0);
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("role delete:: " + result.getInfo());
			return entity;
		}
		
		
		result = roleServicel.deleteRole(uid);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("role delete:: successfully");
		} else {
			logger.error("role delete:: failed Err: " + result.getInfo());
		}
		
		return entity;
		
	}
	
	
	/**
	 * 获取角色列表信息
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/roles", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> getRoles(@RequestParam(value="pageNumber", required=true) Integer pageNumber, 
			@RequestParam(value="limit", required=true) Integer limit,
			@RequestParam(value="keyword", required=false) String keyword, 
			@RequestParam(value="sortField", required=false) String sortField,
			@RequestParam(value="sortType", required=false) String sortType,@RequestHeader(value="Authorization", required=false) String authToken) {
	
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		result = roleServicel.getRoles(pageNumber, limit, sortField, sortType, keyword);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("find roles:: successfully");
		} else {
			logger.error("find roles:: failed Err: " + result.getInfo());
		}
		
		return entity;
	}
	
	
	/**
	 * 角色分配权限
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/role/roleAssignPermission", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> roleAssignPermission(@RequestBody Map<String, Object> input,@RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		if ( !input.containsKey("roleId") || !input.containsKey("permissionIds")) {
			result.setStatus(0);
			result.setCode("");
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("role Assign Permission :: " + result.getInfo());
			return entity;
		}
		
		Object o1 = input.get("roleId");
		Object o2 = input.get("permissionIds");
		
		if ( ! (o1 instanceof Integer)) {
			
			result.setStatus(0);
			result.setInfo("参数类型错误");
			
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("role Assign Permission  :: " + result.getInfo());
			return entity;
		}
		
		Integer roleId = (Integer) input.get("roleId");
		List<Integer> permissionIds = o2 != null ? (List<Integer>) input.get("permissionIds") : null;
		
		result = roleServicel.roleAssignPermission(roleId, permissionIds);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("role Assign Permission:: successfully ");
		} else {
			logger.error("role Assign Permission:: failed Err: " + result.getInfo());
		}
		return entity;
	}
	
	
	/**
	 * 根据角色编号查询权限信息
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/role/{uid}/permissions", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> getPermissions(@PathVariable("uid") Integer uid, @RequestHeader(value="Authorization", required=false) String authToken) {
		
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		if ( uid == null ) {
			result.setStatus(0);
			result.setCode("");
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("get permissions by roleId :: " + result.getInfo());
			return entity;
		}
		
		result = roleServicel.getPermissions(uid);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("get permissions by roleId:: successfully ");
		} else {
			logger.error("get permissions by roleId:: failed Err: " + result.getInfo());
		}
		return entity;
	}

}
