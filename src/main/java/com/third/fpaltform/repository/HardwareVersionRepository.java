package com.third.fpaltform.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.HardwareVersionEntity;

@Component
public interface HardwareVersionRepository extends CrudRepository<HardwareVersionEntity, Integer>
{
	
	/**
	 * 获取版本信息
	 * @param barcode
	 * @param type
	 * @return
	 */
	
	@Query(value="select * from HARDWARE_VERSION where UID = (select max(UID) from HARDWARE_VERSION where MODEL_UID = ?1 AND STATUS = 1) ", nativeQuery=true)
	HardwareVersionEntity findHardwareVersion(Integer modelId);
	


}
