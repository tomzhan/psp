package com.third.fpaltform.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.PermissionEntity;
import com.third.fpaltform.entity.PermissionRoleEntity;
import com.third.fpaltform.entity.RoleEntity;
import com.third.fpaltform.exception.ServiceException;
import com.third.fpaltform.repository.PermissionRepository;
import com.third.fpaltform.repository.PermissionRoleRepository;
import com.third.fpaltform.repository.RoleRepository;
import com.third.fpaltform.rest.Result;

@Component
public class RoleService 
{
	private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PermissionRoleRepository permissionRoleRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;


	/**
	 * 查询用户关联的角色与权限
	 * @param uid
	 * @param uid2
	 * @return
	 */
	public List<RoleEntity> findRoles(Integer userId, Integer divisionId) {
		
		List<Integer> roleIds = roleRepository.findAllRoleIds(userId, divisionId);
		
		List<RoleEntity> roleList = new ArrayList<RoleEntity>();
		
		if(roleIds.size() != 0)
		{
			roleList = roleRepository.findRoles(roleIds);
			
		}
		return roleList;
	}
	/**
	 * 
	 * @param userId
	 * @param divisionId
	 * @return
	 */
	public List<Integer> findAllRoleIds(Integer userId, Integer divisionId){
		List<Integer> roleIds = roleRepository.findAllRoleIds(userId, divisionId);
		if(roleIds.isEmpty()){
			throw new ServiceException("200001");
		}
		return roleIds;
	}

	/**
	 * 新增角色信息
	 * @param roleName
	 * @param remark
	 */
	public Result insertRole(String roleName, String remark) 
	{
		Result result = new Result();

		//验证角色名称是否存在
		RoleEntity r = null;
		
		try {
			r = roleRepository.findByRoleName(roleName);
			
			if(r != null)
			{
				result.setStatus(0);
				result.setInfo("角色名称已存在");
				logger.error(result.getInfo());
				return result;
			}
			
			RoleEntity role = new RoleEntity();
			
			role.setRoleName(roleName);
			role.setRemark(remark);
			role.setCreateTime(new Date());
			
			r = roleRepository.save(role);
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setCode("");
		result.setInfo("角色新增成功");
		result.setStatus(1);
		result.setContent(new JSONObject().put("uid", r.getUid()).toString());
		return result;
		
	}


	/**
	 * 更新角色信息
	 * @param uid
	 * @param roleName
	 * @param remark
	 */
	public Result updateRole(Integer uid, String roleName, String remark) {
		
		
		Result result = new Result();
		
		try {
			//验证角色信息是否存在
			RoleEntity r = roleRepository.findOne(uid);
			
			if(r == null)
			{
				result.setStatus(0);
				result.setCode("");
				result.setInfo("角色信息不存在");
				
				return result;
			}
			
			//验证角色的名称不能存在
			RoleEntity role = roleRepository.checkRoleNameByUid(uid, roleName);
			
			if(role != null)
			{
				result.setStatus(0);
				result.setCode("");
				result.setInfo("角色名称已存在");
				
				return result;
			}
			
			r.setRoleName(roleName == null ? r.getRoleName() : roleName);
			r.setRemark(remark == null ? r.getRemark() : remark);
			r.setUpdateTime(new Date());
			
			roleRepository.save(r);
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setCode("");
		result.setInfo("角色更新成功");
		result.setStatus(1);

		return result;
		
	}


	/**
	 * 删除角色信息
	 * @param uid 
	 */
	public Result deleteRole(Integer uid) 
	{
		
		Result result = new Result();
		
		try {
			RoleEntity r = roleRepository.findOne(uid);
			
			if(r == null){
				result.setStatus(0);
				result.setCode("");
				result.setInfo("角色信息不存在");
				
				return result;
			}
			
			if(r.getRolePermissions() != null && r.getRolePermissions().size() > 0)
			{
				result.setStatus(0);
				result.setCode("");
				result.setInfo("该角色已关联权限,请先删除权限");
				
				return result;
			}
			
			roleRepository.delete(r.getUid());
			
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setCode("");
		result.setInfo("角色删除成功");
		result.setStatus(1);

		return result;
		
	}


	/**
	 * 按条件获取角色信息
	 * @param pageNumber
	 * @param limit
	 * @param sortField
	 * @param sortType
	 * @param keyword
	 */
	public Result getRoles(Integer pageNumber, Integer limit, String sortField, String sortType, String keyword) 
	{
		Result result = new Result();
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			
			Page<RoleEntity> rolePage = null;
			
			if(sortField == null && keyword == null)
			{
				rolePage = roleRepository.findRoles(new PageRequest(pageNumber, limit));
			}else if (sortField != null && keyword == null)
			{
				rolePage = roleRepository.findRoles(new PageRequest(pageNumber, limit, new Sort(Direction.fromString(sortType), sortField)));
			}else if (sortField == null && keyword != null)
			{
				keyword = "%" + keyword + "%";
				rolePage = roleRepository.findByRoleLike(keyword, new PageRequest(pageNumber, limit));
			}else
			{
				keyword = "%" + keyword + "%";
				rolePage = roleRepository.findByRoleLike(keyword, new PageRequest(pageNumber, limit, new Sort(Direction.fromString(sortType), sortField)));
			}
			
			
			List<RoleEntity> roleContent = rolePage.getContent();
			
			List<String> roles = new ArrayList<String>();
			
			for(RoleEntity r : roleContent)
			{
				roles.add(r.toJson());
			}
			
			map.put("roles", roles);
			map.put("totalPages", rolePage.getTotalPages());
			map.put("totalElements", rolePage.getTotalElements());
			map.put("numberOfCurrentPage", rolePage.getNumberOfElements());
			if ( sortField != null && sortType != null ) {
				map.put("sortField", sortField);
				map.put("sortType", sortType.toUpperCase());
			} else {
				map.put("sortField", null);
				map.put("sortType", null);
			}
			map.put("limit", rolePage.getSize());
			map.put("pageNumber", rolePage.getNumber());
			map.put("isFirstPage", rolePage.isFirst());
			map.put("isLastPage", rolePage.isLast());
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setStatus(1);
		result.setContent(map);
		result.setCode("");
		result.setInfo("获取角色信息成功");
		return result;
		
	}


	/**
	 * 角色分配权限
	 * @param roleId
	 * @param permissionIds
	 */
	public Result roleAssignPermission(Integer roleId, List<Integer> permissionIds) 
	{
		
		//TODO
		Result result = new Result();
		
		try {
			permissionRoleRepository.deleteByRoleId(roleId);
			
			RoleEntity r = roleRepository.findOne(roleId);
			
			if(r == null)
			{
				result.setStatus(0);
				result.setCode("");
				result.setInfo("角色信息不存在");
				
				return result;
			}
			
			for(Integer id : permissionIds)
			{
				PermissionEntity p = permissionRepository.findOne(id);
				
				if(p == null)
				{
					result.setStatus(0);
					result.setCode("");
					result.setInfo("权限信息不存在");
					
					return result;
				}
				
				PermissionRoleEntity pr = new PermissionRoleEntity();
				
				pr.setRolePermission(r);
				pr.setPermissionRole(p);
				
				permissionRoleRepository.save(pr);
				
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
		result.setInfo("角色分配权限成功");
		return result;
		
	}


	/**
	 * 根据角色编号查询权限信息
	 * @param uid
	 */
	public Result getPermissions(Integer uid) 
	{
		Result result = new Result();
		
		try {
			RoleEntity r = roleRepository.findRoleByUid(uid);
			
			if(r == null)
			{
				result.setStatus(0);
				result.setCode("");
				result.setInfo("角色信息不存在");
				
				return result;
			}
			
			List<PermissionEntity> permissions = permissionRepository.getPermissions(uid);
			
			for(PermissionEntity p : permissions)
			{
				p.setPermissionRoles(null);
			}
			
			result.setStatus(1);
			result.setCode("");
			result.setContent(permissions);
			
			return result;
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
	}
}
