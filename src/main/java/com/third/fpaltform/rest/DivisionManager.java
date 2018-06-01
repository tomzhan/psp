package com.third.fpaltform.rest;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.third.fpaltform.common.MediaTypes;
import com.third.fpaltform.service.DivisionService;

@RestController
@RequestMapping(value = "/rest")
public class DivisionManager 
{
	private static Logger logger = LoggerFactory.getLogger(DivisionManager.class);
	
	@Autowired
	private DivisionService divisionService;
	
	/**
	 * 增加部门
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/division", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public  ResponseEntity<String> insertDivision(@RequestBody Map<String, Object> input) 
	{
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();

		if ( !input.containsKey("divisionName")) {
			result.setStatus(0);
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("insert division: " + result.getInfo());
			return entity;
		}
		
		Object o1 = input.get("divisionName");//部门名称
		Object o2 = input.get("parentDivisionUid");//父部门编号
		
		if (!(o1 instanceof String)) {

			result.setStatus(0);
			result.setInfo("参数类型错误");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("insert division: " + result.getInfo());
			return entity;
		}
		
		String divisionName = (String) o1;
		Integer parentDivisionUid = (Integer) o2;
		String description = null;
		
		if(input.containsKey("parentDivisionUid") && (input.get("parentDivisionUid") instanceof Integer))
		{
			parentDivisionUid = (Integer) input.get("parentDivisionUid");
		}
		
		if(input.containsKey("description") && (input.get("description") instanceof String))
		{
			description = (String) input.get("description");
		}
		
		//新增部门
		result = divisionService.insert(divisionName, parentDivisionUid, description);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() == 1 ) {
			logger.info("insert division: successfully");
		} else {
			logger.error("insert division: failed Err: " + result.getInfo());
		}
		return entity;
	}
	
	/**
	 * 修改部门
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/division", method = RequestMethod.PUT, produces = MediaTypes.JSON_UTF_8)
	public  ResponseEntity<String> updateDivision(@RequestBody Map<String, Object> input) 
	{
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();

		if (!input.containsKey("uid")) {
			
			result.setStatus(0);
			result.setInfo("参数为空或不完整");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("update division: " + result.getInfo());
			return entity;
		}
		
		Object o1 = input.get("uid");//
		
		if (!(o1 instanceof Integer)) {

			result.setStatus(0);
			result.setInfo("参数类型错误");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("update division: " + result.getInfo());
			
			return entity;
		}
		
		Integer uid = (Integer) o1;
		Integer parentDivisionUid = null;
		String divisionName = null;
		String description = null;
		
		if(input.containsKey("parentDivisionUid") && (input.get("parentDivisionUid") instanceof Integer))
		{
			parentDivisionUid = (Integer) input.get("parentDivisionUid");
		}
		
		if(input.containsKey("divisionName") && (input.get("divisionName") instanceof String))
		{
			divisionName = (String) input.get("divisionName");
		}
		
		if(input.containsKey("description") && (input.get("description") instanceof String))
		{
			description = (String) input.get("description");
		}
		
		//修改部门信息
		result = divisionService.update(uid, divisionName, parentDivisionUid, description);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() == 1 ) {
			logger.info("update division: successfully");
		} else {
			logger.error("update division: failed Err: " + result.getInfo());
		}
		return entity;
	}
	
	/**
	 * 删除部门
	 * @param input
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/division/{uid}", method = RequestMethod.DELETE, produces = MediaTypes.JSON_UTF_8)
	public  ResponseEntity<String> deleteDivision(@PathVariable("uid") Integer uid) 
	{
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();

		if (uid == null) {
			
			result.setStatus(0);
			result.setInfo("参数为空或不完整");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("delete division: " + result.getInfo());
			return entity;
		}
		
		//删除部门信息
		result = divisionService.delete(uid);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() == 1 ) {
			logger.info("delete division: successfully");
		} else {
			logger.error("delete division: failed Err: " + result.getInfo());
		}
		return entity;
	}
	
	
	/**
	 * 获取所有部门
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/divisions", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> selectDivisions() 
	{
		logger.info("Find all divisions");
		
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		result = divisionService.findAll();
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() == 1 ) {
			logger.info("get division: successfully");
		} else {
			logger.error("get division: failed Err: " + result.getInfo());
		}
		return entity;
	}
	
	
	/**
	 * 根据部门UID获取其下一级子部门
	 * @param uid
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value = "/select/divisionDetails", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> selectDivisionByUid(@RequestBody Map<String, Object> input) 
	{
		ResponseEntity<String> entity = null;
		
		JSONObject body = new JSONObject();

		if (!input.containsKey("uid")) {
			
			body.put("status", false);
			body.put("err", "参数为空或不完整");
			entity = new ResponseEntity<String>(body.toString(), HttpStatus.OK);
			
			logger.error("select divisions: " + body.getString("err"));
			return entity;
		}
		
		Object o1 = input.get("uid");//
		
		if (!(o1 instanceof Integer)) {

			body.put("status", false);
			body.put("err", "参数类型错误");
			entity = new ResponseEntity<String>(body.toString(), HttpStatus.OK);

			logger.error("select divisions: " + body.getString("err"));
			return entity;
		}
		
		Integer uid = (Integer) o1;
		
//		JSONObject result = divisionService.selectDivisionByUid(uid);
		JSONObject result = null;
		
		entity = new ResponseEntity<String>(result.toString(), HttpStatus.OK);
		
		if ( body.getBoolean("status") ) {
			logger.info("select divisions: successfully");
		} else {
			logger.error("select divisions: failed Err: " + body.getString("err"));
		}
		
		return entity;
	}
	
	/**
	 * 部门分配角色
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/division/divisionAssignRole", method = RequestMethod.POST, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> divisionAssignRole(@RequestBody Map<String, Object> input,@RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();

		if ( !input.containsKey("divisionId") || !input.containsKey("roleIds")) {
			result.setStatus(0);
			result.setCode("");
			result.setInfo("参数不能为空");
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("division Assign Role :: " + result.getInfo());
			return entity;
		}
		
		Object o1 = input.get("divisionId");
		Object o2 = input.get("roleIds");
		
		if ( ! (o1 instanceof Integer)) {
			
			result.setStatus(0);
			result.setInfo("参数类型错误");
			
			entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
			
			logger.error("division Assign Role  :: " + result.getInfo());
			return entity;
		}
		
		Integer divisionId = (Integer) input.get("divisionId");
		List<Integer> roleIds = o2 != null ? (List<Integer>) input.get("roleIds") : null;
		
		result = divisionService.divisionAssignRole(divisionId, roleIds);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("division Assign Role:: successfully ");
		} else {
			logger.error("division Assign Role:: failed Err: " + result.getInfo());
		}
		return entity;
		
	}
	
	
	/**
	 * 根据父部门编号查询子部门信息
	 * @param input
	 * @param authToken
	 * @return
	 */
	@CrossOrigin
	@RequestMapping(value="/division/{divisionId}", method = RequestMethod.GET, produces = MediaTypes.JSON_UTF_8)
	public ResponseEntity<String> getDivisions(@PathVariable("divisionId") Integer divisionId, @RequestHeader(value="Authorization", required=false) String authToken) {
   
		ResponseEntity<String> entity = null;
		
		Result result = new Result();
		
		Gson json = new Gson();
		
		result = divisionService.getDivisions(divisionId);
		
		entity = new ResponseEntity<String>(json.toJson(result), HttpStatus.OK);
		
		if ( result.getStatus() != 0 ) {
			logger.info("get divisions by parentId :: successfully ");
		} else {
			logger.error("get divisions by parentId:: failed Err: " + result.getInfo());
		}
		return entity;
		
	}
}
