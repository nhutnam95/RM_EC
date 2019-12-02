package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "rfr_effortmonth")
@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RfrEffortMonth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rfr_effortmonth_id", unique = true, nullable = false)
	private Long rfrEffortmonthId;
	
	@Column(name = "rfr_id")
	private Long rfrId;
	
	@Column(name = "effort_name")
	private String effortName;

	@Column(name = "effort")
	private float effort;

}
