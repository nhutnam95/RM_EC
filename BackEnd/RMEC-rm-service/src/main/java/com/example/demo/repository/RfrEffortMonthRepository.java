package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RfrEffortMonth;



@Repository
public interface RfrEffortMonthRepository extends JpaRepository<RfrEffortMonth, Long> {

	List<RfrEffortMonth> findAllRfrEffortMonthByRfrId(Long rfrId);
	Optional<RfrEffortMonth> findByRfrIdAndEffortName(Long rfrId,String effortName);
}
