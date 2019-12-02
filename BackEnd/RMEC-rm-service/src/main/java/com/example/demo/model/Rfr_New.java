package com.example.demo.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.json.simple.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "rfr_new")
@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rfr_New {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rfr_id", unique = true, nullable = false)
	private Long rfrId;
	
	@Column(name = "project_id")
	private Long projectId;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "rank_name")
	private String rankName;
	
	@Column(name = "skill_name")
	private String skillName;
	
	@Column(name = "location_name")
	private String locationName;
	
	@Column(name = "create_date")
	private LocalDate createDate;
	
	@Column(name = "update_date")
	private LocalDate updateDate;
	
	@Column(name = "del_flag")
	private boolean delFlag;
	
	@Transient
	private JSONObject rfrEffortMonths;
}
