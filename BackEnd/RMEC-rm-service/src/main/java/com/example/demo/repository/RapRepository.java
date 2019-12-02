package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.model.Rap;

@Repository
public interface RapRepository extends JpaRepository<Rap, Long> {
	Rap findRapByProjectId(Long projectId);

	@Query("SELECT rap FROM Rap rap WHERE rap.projectId = :projectId AND rap.rapDelFlag = false")
	List<Rap> findAllRapByProjectId(@Param("projectId") Long projectId);

	Rap findRapByRfrId(Long rfrId);
	@Query("SELECT rap FROM Rap rap WHERE rap.rfrId = :rfrId AND rap.accountName = :ratAccount AND rap.rapDelFlag = false" )
	Optional<Rap> findByRfrIdAndAccountName(@Param("rfrId")Long rfrId, @Param("ratAccount")String ratAccount);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query( value ="update Rap r  set r.rapDelFlag = true where projectId  = :projectId and rapId NOT IN (:listRap) " )
	void updateListRapOfProject (@Param("projectId") Long projectId ,@Param("listRap") ArrayList<Long> listRap);
	
}
