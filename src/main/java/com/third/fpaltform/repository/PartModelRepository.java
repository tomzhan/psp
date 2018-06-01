package com.third.fpaltform.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.PartModelEntity;

@Component
public interface PartModelRepository extends CrudRepository<PartModelEntity, Integer>, JpaSpecificationExecutor<PartModelEntity>
{
	

}
