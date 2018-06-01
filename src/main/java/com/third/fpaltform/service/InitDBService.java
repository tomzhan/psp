package com.third.fpaltform.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.RoleEntity;
import com.third.fpaltform.repository.RoleRepository;


@Component
public class InitDBService implements InitializingBean, DisposableBean{
	
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private RoleService roleService;	
	
	@Autowired
	private DivisionService divisionService;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void destroy() throws Exception {
		// do nothing
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {

		
		// add root division if need
		if(divisionService.findAllDivisions().isEmpty()){
			
			String divisionName = "总部";
			divisionService.insert(divisionName, null, "");
		}
		
		// add root user if need
		if(userService.findAll().isEmpty()){
			String userName = "admin";
			String pass = "admin";
			String operator = "admin";
			
			userService.insertUser(userName, pass, operator, 1, "");
		}	
		
		// add role user if need
		if(roleService.findRoles(1, 1).isEmpty()){
			String roleName = "superadmin";
			String remark = "超级用户";
			
			RoleEntity r = roleRepository.findByRoleName(roleName);
			if(r == null)
			{
				roleService.insertRole(roleName, remark);
			}
			
		}
		
	}

}
