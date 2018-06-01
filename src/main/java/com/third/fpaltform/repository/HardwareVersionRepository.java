package com.third.fpaltform.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.HardwareVersionEntity;

@Component
public interface HardwareVersionRepository extends CrudRepository<HardwareVersionEntity, Integer>
{

	@Modifying  
    @Query("update HardwareVersionEntity h set h.status = 0 where h.partModel.uid = ?uid ") 
	void updateHardwareVersion(Integer uid);
	
	/**
	 * 获取版本信息
	 * @param barcode
	 * @param type
	 * @return
	 */
	
	@Query(value="select * from HARDWARE_VERSION where PART_MODEL_UID = ( select UID from PART_MODEL where UID = ?1 ) AND STATUS = 1 ", nativeQuery=true)
	HardwareVersionEntity findHardwareVersion(Integer modelId);
	


}
