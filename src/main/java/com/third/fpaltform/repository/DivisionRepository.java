package com.third.fpaltform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.third.fpaltform.entity.DivisionEntity;


@Component
public interface DivisionRepository extends CrudRepository<DivisionEntity, Integer>
{

	/**
	 * 判断部门名称是否存在
	 * @param divisionName
	 * @return
	 */
	DivisionEntity findByDivisionName(String divisionName);

	/**
	 * 通过部门编号与部门名称判断部门是否存在
	 * @param uid
	 * @param divisionName
	 * @return
	 */
	@Query(value="select d from DivisionEntity d where d.divisionName = :divisionName and d.uid <> :uid")
	DivisionEntity existsByUidAndDivisionName(@Param("uid") Integer uid, @Param("divisionName") String divisionName);

	/**
	 * 查询当前部门的子部门
	 * @param uid
	 * @return
	 */
	@Query(value="select new DivisionEntity(d.uid, d.parentDivisionUid, d.divisionName, d.description, d.createTime, d.updateTime) from DivisionEntity d where d.uid in ?1 ")
	List<DivisionEntity> findByParentDivisionUid(List<Integer> divisionUids);

	/**
	 * 通过部门编号查询所有的子部门
	 * @param divisionUid
	 * @return
	 */
	@Query(value="select queryChildren(:divisionUid)" , nativeQuery=true)
	String getdivisionChildren(@Param("divisionUid") Integer divisionUid);

	@Query(value="select new DivisionEntity(d.uid, d.parentDivisionUid, d.divisionName, d.description, d.createTime, d.updateTime) from DivisionEntity d")
	List<DivisionEntity> findDivisions();

}
