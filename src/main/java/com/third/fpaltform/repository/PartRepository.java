package com.third.fpaltform.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.PartEntity;

@Component
public interface PartRepository extends CrudRepository<PartEntity, Integer>
{

	PartEntity findByBarCode(String barcode);

	@Query("select p from PartEntity p where p.barCode = ?1 and p.type = ?2 ")
	PartEntity findPart(String barcode, Integer type);


}
