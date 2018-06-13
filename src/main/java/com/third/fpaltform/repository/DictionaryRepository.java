package com.third.fpaltform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.DictionaryEntity;
import com.third.fpaltform.entity.DictionaryMultiKeys;


@Component
public interface DictionaryRepository extends CrudRepository<DictionaryEntity, DictionaryMultiKeys>
{
	@Query(value="SELECT * from DICTIONARY WHERE ID=?1 ", nativeQuery=true)
	List<DictionaryEntity> findByUid(Integer id);
}
