package com.third.fpaltform.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.third.fpaltform.entity.VehicleEntity;

public interface VehicleRepository extends CrudRepository<VehicleEntity, Integer>, JpaSpecificationExecutor<VehicleEntity>
{

	VehicleEntity findByVin(String vin);
	
	

}
