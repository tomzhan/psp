package com.third.fpaltform.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.DebugParamEntity;
import com.third.fpaltform.entity.PartEntity;
import com.third.fpaltform.entity.PartModelEntity;
import com.third.fpaltform.exception.ServiceException;
import com.third.fpaltform.repository.DebugParamRepository;
import com.third.fpaltform.repository.PartRepository;

@Component
public class DebugParamService 
{
	@Resource
	private DebugParamRepository debugParamRepository;
	
	@Resource
	private PartRepository partRepository;

	
	/**
	 * 查询参数列表接口
	 * @param barcode
	 * @param type
	 */
	public List<DebugParamEntity> findDebugParams(String barcode, Integer type) 
	{
		PartEntity part = partRepository.findPart(barcode, type);
		
		if(part == null)
		{
			throw new ServiceException("部件型号信息不存在");
		}
		
		List<DebugParamEntity> debugParams = debugParamRepository.findByModelId(part.getUid());
		
		return debugParams;
	}

	/**
	 * 获取参数及曲线图片接口
	 * @param bikeType
	 * @param rate
	 * @param radius
	 * @param model
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	public Page<DebugParamEntity> queryDebugParams(String bikeType, String rate, String radius, String model,
			Integer pageNumber, Integer limit) 
	{
		Sort sort = new Sort(Direction.DESC, "uid");
		Pageable pageable = new PageRequest(pageNumber, limit, sort);
		Page<DebugParamEntity> debugParam = debugParamRepository.findAll(debugParamSpec(bikeType, rate, radius, model), pageable);
		return debugParam;
	}
	
	private Specification<DebugParamEntity> debugParamSpec(final String bikeType, final String rate, final String radius, final String model) 
	{
		return new Specification<DebugParamEntity>(){

			@Override
			public Predicate toPredicate(Root<DebugParamEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<Predicate>(); 
				if(!"".equals(bikeType) && null != bikeType)
				{
					predicates.add(cb.equal(root.<String>get("bikeType"), bikeType));
				}	
				if(!"".equals(rate) && null != rate)
				{
					predicates.add(cb.equal(root.<String>get("rate"), rate));
				}
				if(!"".equals(radius) && null != radius)
				{
					predicates.add(cb.equal(root.<String>get("radius"), radius));
				}
				if(!"".equals(model) && null != model)
				{
					predicates.add(cb.equal(root.<String>get("model"), model));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
	}
}
