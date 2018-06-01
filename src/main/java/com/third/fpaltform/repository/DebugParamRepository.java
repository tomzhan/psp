package com.third.fpaltform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.DebugParamEntity;

@Component
public interface DebugParamRepository extends CrudRepository<DebugParamEntity, Integer>, JpaSpecificationExecutor<DebugParamEntity>
{
	
	/**
	 * 查询参数列表接口
	 * @param modelId
	 */
	@Query("select d from DebugParamEntity d where d.modelId = ?1 ")
	List<DebugParamEntity> findByModelId(Integer modelId);
	

}
