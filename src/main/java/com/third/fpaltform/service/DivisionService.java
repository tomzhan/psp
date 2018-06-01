package com.third.fpaltform.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.DivisionEntity;
import com.third.fpaltform.entity.DivisionRoleEntity;
import com.third.fpaltform.entity.RoleEntity;
import com.third.fpaltform.repository.DivisionRepository;
import com.third.fpaltform.repository.DivisionRoleRepository;
import com.third.fpaltform.repository.RoleRepository;
import com.third.fpaltform.rest.Result;



@Component
public class DivisionService {
	
	@Autowired
	private DivisionRepository divisionRepository;
	@Autowired
	private DivisionRoleRepository divisionRoleRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private RoleService roleService;
	
	private static Logger logger = LoggerFactory.getLogger(DivisionService.class);

	/**
	 * 新增部门
	 * @param divisionName
	 * @param parentDivisionUid
	 * @param description
	 * @return
	 */
	public Result insert(String divisionName, Integer parentDivisionUid, String description) 
	{
		Result result = new Result();
		
		if(parentDivisionUid != null)//增加子部门
		{
			//判断父部门是否存在
			DivisionEntity parent = existsParentDivision(parentDivisionUid);
			
			if(parent == null)
			{
				result.setStatus(0);
				result.setCode("");
				result.setInfo("父部门信息不存在");
				
				return result;
			}
			
		}
		
		//判断部门名称是否存在
		DivisionEntity divisionInfo = existsDivisionName(divisionName);
		
		if(divisionInfo != null)
		{
			result.setStatus(0);
			result.setCode("");
			result.setInfo("部门名称已存在,请重新输入");
			
			return result;
		}
		
		DivisionEntity d = new DivisionEntity();
		d.setDivisionName(divisionName);
		d.setParentDivisionUid(parentDivisionUid);
		d.setCreateTime(new Date());
		
		d = divisionRepository.save(d);
		
		result.setStatus(1);
		result.setCode("");
		result.setInfo("部门新增成功");
		result.setContent(new JSONObject().put("uid", d.getUid()).toString());
		return result;
	}

	/**
	 * 判断部门名称是否存在
	 */
	private DivisionEntity existsDivisionName(String divisionName) 
	{
		DivisionEntity d = divisionRepository.findByDivisionName(divisionName);
		return d;
	}

	/**
	 * 判断是否存在父部门
	 * @param parentDivisionUid 
	 * @return
	 */
	private DivisionEntity existsParentDivision(Integer parentDivisionUid) 
	{
		DivisionEntity d = divisionRepository.findOne(parentDivisionUid);
		return d;
	}

	/**
	 * 修改部门信息
	 * @param uid
	 * @param divisionName
	 * @param parentDivisionUid
	 * @param description
	 * @return
	 */
	public Result update(Integer uid, String divisionName, Integer parentDivisionUid, String description) {
		
		Result result = new Result();
		
		DivisionEntity division = existsByUidAndDivisionName(uid, divisionName);
		
		if(division != null)
		{
			result.setStatus(0);
			result.setCode("");
			result.setInfo("部门名称已存在,请重新输入");
			
			return result;
		}
		
		DivisionEntity d = divisionRepository.findOne(uid);
		
		if(d == null)
		{
			result.setStatus(0);
			result.setCode("");
			result.setInfo("部门信息不存在,请重新输入");
			
			return result;
		}
		
		d.setDivisionName(divisionName == null ? d.getDivisionName() : divisionName);
		d.setParentDivisionUid(parentDivisionUid == null ? d.getParentDivisionUid() : parentDivisionUid);
		d.setDescription(description == null ? d.getDescription(): description);
		
		divisionRepository.save(d);
		
		result.setStatus(1);
		result.setCode("");
		result.setInfo("部门更新成功");
		
		return result;
	}

	/**
	 * 通过部门编号与部门名称判断部门是否存在
	 * @param divisionName 
	 * @param uid 
	 */
	private DivisionEntity existsByUidAndDivisionName(Integer uid, String divisionName) 
	{
		DivisionEntity d = divisionRepository.existsByUidAndDivisionName(uid, divisionName);
		return d;
	}

	/**
	 * 删除部门信息
	 * @param uid
	 * @return
	 */
	public Result delete(Integer uid) 
	{
		Result result = new Result();
		
		DivisionEntity d = divisionRepository.findOne(uid);
		
		if(d == null)
		{
			result.setStatus(0);
			result.setCode("");
			result.setInfo("部门信息不存在,请重新输入");
			
			return result;
		}
		
		if(d.getUsers() == null && d.getUsers().size() == 0)
		{
			divisionRepository.delete(uid);
		}else
		{
			result.setStatus(1);
			result.setCode("");
			result.setInfo("请先删除该部门下的用户");
			
			return result;
		}
		
		result.setStatus(1);
		result.setCode("");
		result.setInfo("部门删除成功");
		
		return result;
	}

	/**
	 * 查询所有部门信息
	 * @return
	 */
	public Result findAll() 
	{
		Result result = new Result();
		
		List<DivisionEntity> divisions = new ArrayList<DivisionEntity>();
		try {
			divisions = divisionRepository.findDivisions();
			
			for(DivisionEntity d : divisions)
			{
				List<RoleEntity> roles = roleService.findRoles(null, d.getUid());
				
				d.setRoles(roles);
			}
		} catch (Exception e) {
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
		}
		
		result.setStatus(1);
		result.setCode("");
		result.setContent(divisions);
		result.setInfo("部门获取成功");
		
		return result;
	}

	/**
	 * 通过部门编号查询所有的子部门
	 * @param divisionUid
	 * @return
	 */
	public List<Integer> getdivisionChildren(Integer divisionUid) 
	{
		String result = divisionRepository.getdivisionChildren(divisionUid);
		
		String[] params = result.split(",");
		
		List<Integer> list = new ArrayList<Integer>();
		
		if(params.length != 0)
		{
			for(String str : params)
			{
				list.add(Integer.parseInt(str));
			}
		}
		
		return list;
	}

	public DivisionEntity findByUid(Integer divisionId) {
		
		return divisionRepository.findOne(divisionId);
	}

	
	/**
	 * 部门分配角色
	 * @param divisionId
	 * @param roleIds
	 */
	public Result divisionAssignRole(Integer divisionId, List<Integer> roleIds) 
	{
		//TODO
		Result result = new Result();
		
		try {
			divisionRoleRepository.deleteByDivisionId(divisionId);
			
			DivisionEntity d = divisionRepository.findOne(divisionId);
			
			if(d == null)
			{
				result.setStatus(0);
				result.setCode("");
				result.setInfo("部门信息不存在");
				
				return result;
			}
			
			for(Integer roleId : roleIds)
			{
				RoleEntity r = roleRepository.findOne(roleId);
				
				if(r == null)
				{
					result.setStatus(0);
					result.setCode("");
					result.setInfo("角色信息不存在");
					
					return result;
				}
				
				DivisionRoleEntity userRole = new DivisionRoleEntity();
				userRole.setRoleDivision(r);
				userRole.setDivisionRole(d);
				
				divisionRoleRepository.save(userRole);
			}
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setStatus(1);
		result.setCode("");
		result.setInfo("部门分配角色成功");
		return result;
		
	}

	/**
	 * 根据父部门编号查询子部门信息
	 * @param parentId
	 * @return
	 */
	public Result getDivisions(Integer divisionUid) 
	{
		Result result = new Result();
		
		List<DivisionEntity> divisions = null;
		
		try {
			
			List<Integer> divisionUids = getdivisionChildren(divisionUid);
			
			divisions = divisionRepository.findByParentDivisionUid(divisionUids);
			
			for(DivisionEntity d : divisions)
			{
				List<RoleEntity> roles = roleService.findRoles(null, d.getUid());
				
				d.setRoles(roles);
			}
			
			
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setStatus(1);
		result.setCode("");
		result.setInfo("查询子部门信息");
		result.setContent(divisions);
		return result;
	}
	
	
	public DivisionEntity findOne(Integer divisionUid) 
	{
		return divisionRepository.findOne(divisionUid);
	}

	public List<DivisionEntity> findAllDivisions() 
	{
		return (List<DivisionEntity>) divisionRepository.findAll();
	}

}
