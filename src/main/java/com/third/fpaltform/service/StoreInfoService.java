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

import com.third.fpaltform.entity.PartModelEntity;
import com.third.fpaltform.entity.StoreInfoEntity;
import com.third.fpaltform.exception.ServiceException;
import com.third.fpaltform.repository.StoreInfoRepository;

@Component
public class StoreInfoService 
{
	@Resource
	private StoreInfoRepository storeInfoRepository;

	
	/**
	 * 新增门店信息
	 * @param name
	 * @param nation
	 * @param unit
	 * @param city
	 * @param addr
	 * @param contact
	 * @param email
	 * @param phone
	 * @param mobile
	 * @param size
	 * @return
	 */
	public StoreInfoEntity insertStoreInfo(String name, String nation, String unit, String city, String addr,
			String contact, String email, String phone, String mobile, Integer size, Integer divisionUid) 
	{
		
		StoreInfoEntity storeInfo = storeInfoRepository.findByName(name);
		
		if(storeInfo != null)
		{
			throw new ServiceException("门店名称已存在");
		}
		
		storeInfo = new StoreInfoEntity();
		storeInfo.setName(name);
		storeInfo.setNation(nation);
		storeInfo.setUnit(unit);
		storeInfo.setCity(city);
		storeInfo.setAddr(addr);
		storeInfo.setContact(contact);
		storeInfo.setEmail(email);
		storeInfo.setPhone(phone);
		storeInfo.setMobile(mobile);
		storeInfo.setSize(size);
		storeInfo.setDivisionUid(divisionUid);
		
		storeInfo = storeInfoRepository.save(storeInfo);
		
		return storeInfo;
	}

	/**
	 * 修改门店信息
	 * @param uid
	 * @param name
	 * @param nation
	 * @param unit
	 * @param city
	 * @param addr
	 * @param contact
	 * @param email
	 * @param phone
	 * @param mobile
	 * @param size
	 */
	public void updateStoreInfo(Integer uid, String name, String nation, String unit, String city, String addr,
			String contact, String email, String phone, String mobile, Integer size) 
	{
		StoreInfoEntity storeInfo = storeInfoRepository.findOne(uid);
		
		if(storeInfo == null)
		{
			throw new ServiceException("门店信息不存在");
		}
		
		StoreInfoEntity s = storeInfoRepository.checkStoreInfo(uid, name);
		
		if(s != null)
		{
			throw new ServiceException("门店名称已存在");
		}
		
		storeInfo.setName(name);
		storeInfo.setNation(nation);
		storeInfo.setUnit(unit);
		storeInfo.setCity(city);
		storeInfo.setAddr(addr);
		storeInfo.setContact(contact);
		storeInfo.setEmail(email);
		storeInfo.setPhone(phone);
		storeInfo.setMobile(mobile);
		storeInfo.setSize(size);
		
		storeInfoRepository.save(storeInfo);
	}

	
	/**
	 * 删除门店信息
	 * @param uid
	 */
	public void deleteStoreInfo(Integer uid) 
	{
		StoreInfoEntity storeInfo = storeInfoRepository.findOne(uid);
		
		if(storeInfo == null)
		{
			throw new ServiceException("门店信息不存在");
		}
		
		storeInfoRepository.delete(storeInfo);
		
	}
	
	/**
	 * 查询门店信息
	 * @param nation
	 * @param city
	 * @param pageNumber
	 * @param limit
	 * @return
	 */
	public Page<StoreInfoEntity> findStoreInfos(String nation, String city, Integer pageNumber, Integer limit) 
	{
		Sort sort = new Sort(Direction.DESC, "uid");
		Pageable pageable = new PageRequest(pageNumber, limit, sort);
		Page<StoreInfoEntity> storeInfoPage = storeInfoRepository.findAll(storeInfoSpec(nation, city), pageable);
		return storeInfoPage;
	}

	private Specification<StoreInfoEntity> storeInfoSpec(final String nation, final String city) 
	{
		return new Specification<StoreInfoEntity>(){

			@Override
			public Predicate toPredicate(Root<StoreInfoEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				List<Predicate> predicates = new ArrayList<Predicate>(); 
				if(!"".equals(nation) && null != nation)
				{
					predicates.add(cb.equal(root.<String>get("nation"), nation));
				}	
				if(!"".equals(city) && null != city)
				{
					predicates.add(cb.equal(root.<String>get("city"), city));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
	}

}
