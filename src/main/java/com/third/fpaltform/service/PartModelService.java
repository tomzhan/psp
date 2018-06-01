package com.third.fpaltform.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

import com.third.fpaltform.entity.HardwareVersionEntity;
import com.third.fpaltform.entity.OperationLogEntity;
import com.third.fpaltform.entity.PartModelEntity;
import com.third.fpaltform.exception.ServiceException;
import com.third.fpaltform.repository.PartModelRepository;

@Component
public class PartModelService 
{
	@Resource
	private PartModelRepository partModelRepository;

	/**
	 * 新增部件类型信息
	 * @param type
	 * @param modelId
	 * @param manufactor
	 * @param meno
	 */
	public PartModelEntity insertPartModel(Integer type, String modelId, String manufactor, String meno, String name) 
	{
		PartModelEntity partModel = new PartModelEntity();
		partModel.setType(type);
		partModel.setName(name);
		partModel.setModelId(modelId);
		partModel.setMeno(meno);
		partModel.setName(name);
		partModel.setCreateTime(new Date());
		
		partModel = partModelRepository.save(partModel);
		
		return partModel;
	}

	
	/**
	 * 修改部件类型信息
	 * @param partModelUid
	 * @param type
	 * @param modelId
	 * @param manufactor
	 * @param meno
	 * @param name
	 */
	public void updatePartModel(Integer partModelUid, Integer type, String modelId, String manufactor, String meno,
			String name) 
	{
		PartModelEntity partModel = partModelRepository.findOne(partModelUid);
		
		if(partModel == null)
		{
			throw new ServiceException("部件型号信息不存在");
		}
		
		partModel.setType(type);
		partModel.setModelId(modelId);
		partModel.setManufactor(manufactor);
		partModel.setMeno(meno);
		partModel.setName(name);
		
		partModelRepository.save(partModel);
	}

	/**
	 * 删除部件信息
	 * @param uid
	 */
	public void deletePartModel(Integer uid) 
	{
		PartModelEntity partModel = partModelRepository.findOne(uid);
		
		if(partModel == null)
		{
			throw new ServiceException("部件型号信息不存在");
		}
		
		Set<HardwareVersionEntity> hardwareVersions = partModel.getHardwareVersions();
		
		if(hardwareVersions != null && hardwareVersions.size() != 0)
		{
			throw new ServiceException("部件型号信息有相关联的版本信息,不能删除");
		}
		
		partModelRepository.delete(uid);
	}

	/**
	 * 查询部件类型信息
	 * @param name
	 * @param maufactory
	 * @param model
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	public Page<PartModelEntity> findPartModels(String name, String maufactory, String model, Integer pageNumber,
			Integer limit) 
	{
		Sort sort = new Sort(Direction.DESC, "createTime");
		Pageable pageable = new PageRequest(pageNumber, limit, sort);
		Page<PartModelEntity> partModelPage = partModelRepository.findAll(partModelSpec(name, maufactory, model), pageable);
		return partModelPage;
	}


	private Specification<PartModelEntity> partModelSpec(final String name, final String maufactory, final String model) 
	{
		return new Specification<PartModelEntity>(){

			@Override
			public Predicate toPredicate(Root<PartModelEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<Predicate>(); 
				if(!"".equals(name) && null != name)
				{
					predicates.add(cb.equal(root.<String>get("name"), name));
				}	
				if(!"".equals(maufactory) && null != maufactory)
				{
					predicates.add(cb.equal(root.<String>get("maufactory"), maufactory));
				}
				if(!"".equals(model) && null != model)
				{
					predicates.add(cb.equal(root.<String>get("modelId"), model));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
	}


}
