package com.third.fpaltform.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.third.fpaltform.repository.PermissionRepository;
import com.third.fpaltform.rest.Result;

@Component
public class PermissionService 
{
	private static final Logger logger = LoggerFactory.getLogger(PermissionService.class);
	
	@Autowired
	private PermissionRepository permissionRepository;

	/**
	 * 查询角色对应的权限
	 * @param roleIds
	 * @return
	 */

	public Set<String> findPermissons(List<Integer> roleIds) {
		Set<String> permissons = permissionRepository.findPermissons(roleIds);
		
		logger.info("权限查询成功");
		return permissons;
	}

	
	/**
	 * 新增权限
	 * @param permissionName
	 * @param permissionIdentify
	 * @param remark
	 */
	public Result insertPermission(String permissionName, String permissionIdentify, String remark) 
	{
		Result result = new Result();
		PermissionEntity p = null;
		try {
			//验证权限名称
			p = permissionRepository.findByPermissionName(permissionName);
			
			if(p != null)
			{
				result.setStatus(0);
				result.setInfo("权限名称已存在");
				logger.error(result.getInfo());
				return result;
			}
			
			p = new PermissionEntity();
			p.setPermissionName(permissionName);
			p.setPermissionIdentify(permissionIdentify);
			p.setCreateTime(new Date());
			p.setRemark(remark);
			
			p = permissionRepository.save(p);
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setCode("");
		result.setInfo("权限新增成功");
		result.setStatus(1);
		result.setContent(new JSONObject().put("uid", p.getUid()).toString());
		return result;
	}


	/**
	 * 编辑权限
	 * @param uid
	 * @param permissionName
	 * @param permissionIdentify
	 * @param remark
	 */
	public Result updatePermission(Integer uid, String permissionName, String permissionIdentify, String remark) 
	{
		
		Result result = new Result();
		
		try {
			PermissionEntity p = permissionRepository.findOne(uid);
			
			if(p == null)
			{
				result.setStatus(0);
				result.setInfo("权限信息不存在");
				logger.error(result.getInfo());
				return result;
			}
			
			//验证权限名称是否存在
			PermissionEntity permission = permissionRepository.checkPermission(uid, permissionName);
			
			if(permission != null)
			{
				result.setStatus(0);
				result.setInfo("权限名称已存在");
				logger.error(result.getInfo());
				return result;
			}
			
			p.setPermissionName(permissionName == null ? p.getPermissionName() : permissionName);
			p.setPermissionIdentify(permissionIdentify == null ? p.getPermissionIdentify() : permissionIdentify);
			p.setRemark(remark == null ? p.getRemark() : remark);
			p.setUpdateTime(new Date());
			
			permissionRepository.save(p);
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setCode("");
		result.setInfo("权限更新成功");
		result.setStatus(1);

		return result;
		
	}


	/**
	 * 删除权限
	 * @param uid
	 */
	public Result deletePermission(Integer uid) 
	{
		Result result = new Result();
		
		try {
			PermissionEntity p = permissionRepository.findOne(uid);
			
			if(p == null)
			{
				result.setStatus(0);
				result.setInfo("权限信息不存在");
				logger.error(result.getInfo());
				return result;
			}
			
			permissionRepository.delete(uid);
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setCode("");
		result.setInfo("权限删除成功");
		result.setStatus(1);

		return result;
	}


	/**
	 * 查询权限信息
	 * @param pageNumber
	 * @param limit
	 * @param sortField
	 * @param sortType
	 * @param keyword
	 */
	public Result getPermissions(Integer pageNumber, Integer limit, String sortField, String sortType, String keyword) 
	{
		Result result = new Result();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			Page<PermissionEntity> permissionPage = null;
			
			if(sortField == null && keyword == null)
			{
				permissionPage = permissionRepository.findAll(new PageRequest(pageNumber, limit));
			}else if (sortField != null && keyword == null)
			{
				permissionPage = permissionRepository.findAll(new PageRequest(pageNumber, limit, new Sort(Direction.fromString(sortType), sortField)));
			}else if (sortField == null && keyword != null)
			{
				keyword = "%" + keyword + "%";
				permissionPage = permissionRepository.findByRoleLike(keyword, new PageRequest(pageNumber, limit));
			}else
			{
				keyword = "%" + keyword + "%";
				permissionPage = permissionRepository.findByRoleLike(keyword, new PageRequest(pageNumber, limit, new Sort(Direction.fromString(sortType), sortField)));
			}
			
			List<PermissionEntity> permissionContent = permissionPage.getContent();
			
			List<String> roles = new ArrayList<String>();
			
			for(PermissionEntity p : permissionContent)
			{
				roles.add(p.toJson());
			}
			
			map.put("roles", roles);
			map.put("totalPages", permissionPage.getTotalPages());
			map.put("totalElements", permissionPage.getTotalElements());
			map.put("numberOfCurrentPage", permissionPage.getNumberOfElements());
			if ( sortField != null && sortType != null ) {
				map.put("sortField", sortField);
				map.put("sortType", sortType.toUpperCase());
			} else {
				map.put("sortField", null);
				map.put("sortType", null);
			}
			map.put("limit", permissionPage.getSize());
			map.put("pageNumber", permissionPage.getNumber());
			map.put("isFirstPage", permissionPage.isFirst());
			map.put("isLastPage", permissionPage.isLast());
		} catch (Exception e) {
			logger.error("系统异常", e.getMessage());
			result.setStatus(0);
			result.setCode("");
			result.setInfo("系统异常");
			
			return result;
		}
		
		result.setCode("");
		result.setInfo("权限查询成功");
		result.setStatus(1);
		result.setContent(map);

		return result;
		
	}


	public Set<String> findAll() {
		return permissionRepository.findAllPermissions();
	}

}
