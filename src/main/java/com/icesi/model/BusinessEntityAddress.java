package com.icesi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
public class BusinessEntityAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "BUSINESSENTITYADDRESS_BUSINESSENTITYADDRESSID_GENERATOR", allocationSize = 1, sequenceName = "BUSINESSENTITYADDRESS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESSENTITYADDRESS_BUSINESSENTITYADDRESSID_GENERATOR")
	private Integer id;

	private LocalDate modifieddate;

	private Integer rowguid;

	@ManyToOne
	private Address address;

	@ManyToOne
	private AddressType addresstype;

	@ManyToOne
	private BusinessEntity businessentity;
	
	@NotBlank(groups = BasicInfo.class)
	private String nameb;
	
	@NotBlank(groups = BasicInfo.class)
	private String namea;
	
	@NotBlank(groups = BasicInfo.class)
	private String addressline1;
	
	@NotBlank(groups = BasicInfo.class)
	private String city;
	
	@NotBlank(groups = BasicInfo.class)
	private String postalcode;

	public BusinessEntityAddress() {
	}
}