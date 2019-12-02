package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Rfr_New;

@Repository
public interface Rfr_New_Repository extends JpaRepository<Rfr_New, Long>  {
	@Query("SELECT e FROM Rfr_New e WHERE e.projectId = :projectId AND e.delFlag = false")
	List<Rfr_New> findAllRfr_NewByProjectId(@Param("projectId") Long projectId);
	@Query("SELECT e.delFlag FROM Rfr_New e WHERE e.rfrId = :rfrId")
	boolean checkDelFlagRfrById(@Param("rfrId") Long rfrId); 
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query( value ="update Rfr_New r  set r.delFlag = true where projectId  = :projectId and rfrId NOT IN (:listRfr) " )
	void updateListRfrOfProject (@Param("projectId") Long projectId ,@Param("listRfr") ArrayList<Long> listRfr);
	

}
