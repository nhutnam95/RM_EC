package com.example.demo.model;

import java.time.LocalDate;
import java.util.Date;

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

@Table(name = "rats")
@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rat_id", unique = true, nullable = false)
	private Long ratId;

	@Column(name = "project_id")
	private Long projectId;

	@Column(name = "rfr_id")
	private Long rfrId;

	@Column(name = "rat_account")
	private String ratAccount;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "rank_id")
	private Long rankId;
	
	@Column(name = "location_id")
	private Long locationId;
	
	@Column(name = "rat_from")
	private LocalDate ratFrom;

	@Column(name = "rat_to")
	private LocalDate ratTo;
	
	@Column(name = "rat_status")
	private int ratStatus;
	
	@Column(name = "rat_date")
	private LocalDate ratDate;

	@Column(name = "rat_create_date")
	private LocalDate ratCreateDate;

	@Column(name = "rat_update_date")
	private LocalDate ratUpdateDate;

	@Column(name = "rat_del_flag")
	private boolean ratDelFlag;
}
