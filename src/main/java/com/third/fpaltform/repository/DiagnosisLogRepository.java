package com.third.fpaltform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.DiagnosisLog;

@Component
public interface DiagnosisLogRepository extends CrudRepository<DiagnosisLog, Integer>
{
	
	/**
	 * 查询车辆部件检测数据
	 * @param v
	 * @param pageRequest
	 * @return
	 */
	@Query("select d from DiagnosisLog d where d.vehicle.uid = :uid ")
	Page<DiagnosisLog> findByVehicleUid(@Param("uid") Integer uid, Pageable pageable);
	


}
