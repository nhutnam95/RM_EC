//package com.fa.ec.configuration.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import com.fa.ec.configuration.model.Station;
//
//@Repository
//public interface StationRepository extends JpaRepository<Station, Long> {
//
//	Station findStationById(Long id);
//
//	List<Station> findAll();
//
//	Station findByPositionAndLineId(long position, long lineId);
//
//	List<Station> findAllByLineId(Long id);
//
//	List<Station> findStationByLineId(Long lineId);
//}
