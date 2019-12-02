//package com.example.demo.model;
//
//import com.fa.ec.common.dto.BaseDto;
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import javax.persistence.*;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//@Table(name = "station")
//@Setter
//@Getter
//@Entity
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class Station extends BaseDto {
//
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id", unique = true, nullable = false)
//	private Long id;
//
//	@Column(name = "factory_id")
//	private long factoryId;
//
//	@Column(name = "station_name")
//	private String stationName;
//
//	@Column(name = "line_id")
//	private long lineId;
//
//	@Column(name = "position")
//	private long position;
//}
