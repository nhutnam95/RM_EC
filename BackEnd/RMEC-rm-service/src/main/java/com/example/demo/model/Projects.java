package com.example.demo.model;

import java.time.LocalDate;
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

@Table(name = "projects")
@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Projects {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id", unique = true, nullable = false)
	private Long projectId;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "project_from")
	private LocalDate projectFrom;

	@Column(name = "project_to")
	private LocalDate projectTo;
	
	@Column(name = "project_create_date")
	private LocalDate projectCreateDate;

	@Column(name = "project_update_date")
	private LocalDate projectUpdateDate;

	@Column(name = "project_del_flag")
	private boolean projectDelFlag;
}
