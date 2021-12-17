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
	
	private String nameb;
	
	private String namea;
	
	private String addressline1;
	
	private String city;
	
	private String postalcode;

	public BusinessEntityAddress() {
	}
}