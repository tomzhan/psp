package com.third.fpaltform.repository;

import org.springframework.data.repository.CrudRepository;

import com.third.fpaltform.entity.VehicleEntity;

public interface VehicleRepository extends CrudRepository<VehicleEntity, Integer>
{

	VehicleEntity findByVin(String vin);
	
	

}
