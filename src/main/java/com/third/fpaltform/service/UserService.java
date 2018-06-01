package com.third.fpaltform.service;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import com.third.fpaltform.common.MD5Utils;
import com.third.fpaltform.entity.DivisionEntity;
import com.third.fpaltform.entity.RoleEntity;
import com.third.fpaltform.entity.UserEntity;
import com.third.fpaltform.entity.UserRoleEntity;
import com.third.fpaltform.exception.ServiceException;
import com.third.fpaltform.repository.RoleRepository;
import com.third.fpaltform.repository.UserRepository;
import com.third.fpaltform.repository.UserRoleRepository;
import com.third.fpaltform.security.BearerToken;

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DivisionService divisionService;
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	PermissionService permissionService;
	
	
	public List<UserEntity> findAll(){
		return (List<UserEntity>)userRepository.findAll();
	}

	public UserEntity findByToken(String token){
		
		UserEntity user = userRepository.findByToken(token);
		
		if(user == null)
			throw new ServiceException("800019");
		
		return user;
	}
	
	
	/**
	 * 重置密码
	 * @param account
	 * @param passwd
	 * @param newPasswd
	 * @return
	 */
	public void resetPwd(String account, String passwd, String newPasswd) 
	{
		UserEntity u = userRepository.findByAccount(account);
		
		if(u == null)
		{
			throw new ServiceException("100003");
		}else if(!u.getPassWord().equals(MD5Utils.encrypt(passwd)))
		{
			throw new ServiceException("100004");
		}
		u.setPassWord( MD5Utils.encrypt(newPasswd));
		userRepository.save(u);
	}

	/**
	 * 
	 * @param account
	 * @return
	 */
	public UserEntity findByAccount(String account) {
		return userRepository.findByAccount(account);
	}

	/**
	 * 保存用户信息
	 * @param user
	 */
	public Integer insertUser(String userName, String password, String operator, Integer divisionId, String remark) {
		UserEntity u = userRepository.findByAccount(userName);

		if (u != null) {
			throw new ServiceException("100001");
		}
		
		DivisionEntity d = null;
		if(null != divisionId){
			d = divisionService.findByUid(divisionId);
			if (d == null) {
				throw new ServiceException("300001");
			}
		}
		
		u = new UserEntity();
		
		u.setAccount(userName);
		u.setCreateTime(new Date());
		u.setPassWord(MD5Utils.encrypt(password));
		u.setOperator(operator);
		u.setDivision(d);
		u.setRemark(remark);
		u.setStatus(UserEntity.STATUS_ACTIVATE);

		userRepository.save(u);
		Integer uid = u.getUid();
		if(null == uid){
			throw new ServiceException("100002");
		}
		return uid;
	}

	/**
	 * 更新用户信息
	 * @param userId 
	 * @param userName
	 * @param operator
	 * @param operator2 
	 * @param divisionId
	 * @param remark
	 */
	public void updateUser(Integer userId, String userName, String password, String operator, Integer divisionId, String remark) {
		UserEntity u = userRepository.findByUid(userId);
		if(u == null)
		{
			throw new ServiceException("100003");
		}
		//判断用户名是否存在
		UserEntity checkUser = userRepository.checkUserAccount(userId, userName);
		if (checkUser != null) {
			throw new ServiceException("100001");
		}
		DivisionEntity d = null;
		if(divisionId != null)
		{
			d = divisionService.findByUid(divisionId);
		}
		u.setAccount(userName == null ? u.getAccount() : userName);
		u.setOperator(operator);
		u.setPassWord(password == null ? u.getPassWord() : password);
		u.setDivision(d == null ? u.getDivision() : d);
		u.setRemark(remark == null ? u.getRemark() : remark);
		userRepository.save(u);
	}
	/**
	 * 删除或者禁用用户信息
	 * @param userId
	 * @param operator
	 * @param status
	 */
	public void updateUserStatus(Integer userId, Integer status) 
	{
		UserEntity u = userRepository.findByUid(userId);
		
		if(u == null)
		{
			throw new ServiceException("100003");
		}
		u.setStatus(status);
		userRepository.save(u);
	}

	/**
	 * 通过uid查询用户信息
	 * @param uid
	 */
	public UserEntity findByUid(Integer uid) {
		UserEntity u = userRepository.findByUid(uid);
		if(u == null)
		{
			throw new ServiceException("100003");
		}
		return u;
	}
	/**
	 * 查询所有用户
	 * @param divisionUid
	 * @param pageNumber
	 * @param limit
	 * @param sortField
	 * @param sortType
	 * @param keyword
	 */
	public Page<UserEntity> findUsers(Integer divisionUid, Integer pageNumber, Integer limit, String sortField, String sortType, String keyword) 
	{
		Page<UserEntity> userPage = null;
		if(sortField == null && keyword == null)
		{
			if(divisionUid == null)
			{
				userPage = userRepository.findByDivisionUid(new PageRequest(pageNumber, limit));
			}else
			{
				userPage = userRepository.findByDivisionUid(divisionUid, new PageRequest(pageNumber, limit));
			}
		}else if (sortField != null && keyword == null)
		{
			if(divisionUid == null)
			{
				userPage = userRepository.findByDivisionUid(new PageRequest(pageNumber, limit, new Sort(Direction.fromString(sortType), sortField)));
			}else
			{
				userPage = userRepository.findByDivisionUid(divisionUid, new PageRequest(pageNumber, limit, new Sort(Direction.fromString(sortType), sortField)));
			}
		}else if (sortField == null && keyword != null)
		{
			keyword = "%" + keyword + "%";
			if(divisionUid == null)
			{
				userPage = userRepository.findByAccountLike(keyword, new PageRequest(pageNumber, limit));
			}else
			{
				userPage = userRepository.findByAccountLike(divisionUid, keyword, new PageRequest(pageNumber, limit));
			}
		}else
		{
			keyword = "%" + keyword + "%";
			if(divisionUid == null)
			{
				userPage = userRepository.findByAccountLike(keyword, new PageRequest(pageNumber, limit, new Sort(Direction.fromString(sortType), sortField)));
			}else
			{
				userPage = userRepository.findByAccountLike(divisionUid, keyword, new PageRequest(pageNumber, limit, new Sort(Direction.fromString(sortType), sortField)));
			}
		}
		return userPage;
	}

	/**
	 * 用户分配角色
	 * @param userId
	 * @param roleIds
	 */
	@Transactional
	public void userAssignRole(Integer userId, List<Integer> roleIds) 
	{
		userRoleRepository.deleteByUserId(userId);
		UserEntity u = userRepository.findOne(userId);
		if(u == null)
		{
			throw new ServiceException("100003");
		}
		for(Integer roleId : roleIds)
		{
			RoleEntity r = roleRepository.findOne(roleId);
			if(r == null)
			{
				throw new ServiceException("200001");
			}
			UserRoleEntity userRole = new UserRoleEntity();
			userRole.setRoleUser(r);
			userRole.setUserRole(u);
			userRoleRepository.save(userRole);
		}
	}

	/**
	 * 更新用户信息
	 * @param user
	 */
	public void save(UserEntity user) {
		userRepository.save(user);
	}
	
	/**
	 * 获取授权信息
	 * @param uid
	 * @param divisionId
	 */
	public UserEntity getAuthorizationInfo(String authToken) 
	{
		BearerToken token = BearerToken.parseToken(authToken);
		
		if(token == null)
		{
			throw new ServiceException("token信息异常");
		}
		
		UserEntity u = userRepository.findByAccount((String)token.getPrincipal());
		if(null == u){
			throw new ServiceException("800017");
		}
		return u;
	}

	/**
	 * 获取用户所具有的的权限
	 * @param userName
	 */
	public Set<String> findUserPermission(String userName) 
	{
		UserEntity u = userRepository.findByAccount(userName);
		
		Set<String> permissons = new HashSet<>();
		
		if(u == null)
		{
			throw new ServiceException("token信息异常");
		}
		
		//查询用户所具有的的权限
		List<Integer> roleIds = roleRepository.findAllRoleIds(u.getUid(), u.getDivisionUid());
		
		if(! roleIds.isEmpty())
		{
			permissons = permissionService.findPermissons(roleIds);
		}
		
		return permissons;
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(MD5Utils.encrypt("phylion2003"));
	}
}
