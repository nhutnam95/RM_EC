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

@Table(name = "raps")
@Setter
@Getter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Rap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rap_id", unique = true, nullable = false)
	private Long rapId;

	@Column(name = "project_id")
	private Long projectId;

	@Column(name = "rfr_id")
	private Long rfrId;

	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "rap_from")
	private String From;

	@Column(name = "rap_to")
	private String To;
	
	@Column(name = "rap_fte")
	private Float Fte;
	
	@Column(name = "rap_status")
	private String Status;

	@Column(name = "rap_create_date")
	private LocalDate rapCreateDate;

	@Column(name = "rap_update_date")
	private LocalDate rapUpdateDate;

	@Column(name = "rap_del_flag")
	private boolean rapDelFlag;

}
