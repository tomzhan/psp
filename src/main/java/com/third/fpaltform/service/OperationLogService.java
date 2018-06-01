package com.third.fpaltform.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.OperationLogEntity;
import com.third.fpaltform.repository.OperationLogRepository;
@Component
public class OperationLogService {
	@Autowired
	private OperationLogRepository operationLogRepository;
	/**
	 * 添加操作日志信息
	 * @param operationUid
	 * @param userUid
	 * @param userAccount
	 * @param operationTime
	 * @param requestUri
	 * @param operationName
	 * @param success
	 * @return
	 */
	public Integer insertOperationLog(String account, Date time, String operation, String type,
			String model){
		OperationLogEntity operationLog = new OperationLogEntity(account, time, operation, type, model);
		operationLogRepository.save(operationLog);
		return operationLog.getUid();
	}
	
	
	public Specification<OperationLogEntity> driverSpec(final String account, final String type,
			final String model, final Date startTime, final Date endTime){
		return new Specification<OperationLogEntity>(){

			@Override
			public Predicate toPredicate(Root<OperationLogEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<Predicate>(); 
				if(!"".equals(account) && null != account)
				{
					predicates.add(cb.equal(root.<String>get("account"), account));
				}	
				if(!"".equals(type) && null != type)
				{
					predicates.add(cb.equal(root.<String>get("type"), type));
				}
				if(!"".equals(model) && null != model)
				{
					predicates.add(cb.equal(root.<String>get("model"), model));
				}
				if(null != startTime)
				{
					predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("time"), startTime));
				}
				if(null != endTime)
				{
					predicates.add(cb.lessThanOrEqualTo(root.<Date>get("time"), endTime));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
		
	}
	/**
	 * 根据登录账号、操作类型、操作模型、操作时间查询操作日志信息(分页)
	 * @param account
	 * @param type
	 * @param model
	 * @param startTime
	 * @param endTime
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	public Page<OperationLogEntity> findByAccountWithTypeWithModelWithTime(String account, String type,
			String model, Date startTime, Date endTime, Integer pageNumber,Integer limit){
		Sort sort = new Sort(Direction.DESC, "time");
		Pageable pageable = new PageRequest(pageNumber, limit, sort);
		Page<OperationLogEntity> appVersionPage = operationLogRepository.findAll(driverSpec(account, type, model, startTime, endTime), pageable);
		return appVersionPage;
	}
}