package com.third.fpaltform.repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.OperationLogEntity;

@Component
public interface OperationLogRepository extends CrudRepository<OperationLogEntity, Integer>, JpaSpecificationExecutor<OperationLogEntity>{
}
