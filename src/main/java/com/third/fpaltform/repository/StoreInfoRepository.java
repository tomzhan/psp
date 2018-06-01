package com.third.fpaltform.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.StoreInfoEntity;

@Component
public interface StoreInfoRepository extends CrudRepository<StoreInfoEntity, Integer>, JpaSpecificationExecutor<StoreInfoEntity>
{

	StoreInfoEntity findByName(String name);

	@Query("select s from StoreInfoEntity s where s.uid <> ?1 and s.name = ?2 ")
	StoreInfoEntity checkStoreInfo(Integer uid, String name);
	

}
