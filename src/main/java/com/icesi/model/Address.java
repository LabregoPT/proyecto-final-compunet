package com.icesi.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADDRESS_ADDRESSID_GENERATOR", allocationSize = 1, sequenceName = "ADDRESS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_ADDRESSID_GENERATOR")
	private Integer addressid;
	
	@NotBlank(groups = BasicInfo.class)
	private String addressline1;

	private String addressline2;
	
	@NotBlank(groups = BasicInfo.class)
	private String city;

	private Timestamp modifieddate;
	
	@NotBlank(groups = BasicInfo.class)
	private String postalcode;

	private Integer rowguid;

	private String spatiallocation;

	// bi-directional many-to-one association to Stateprovince
	@ManyToOne
	@JoinColumn(name = "stateprovinceid")
	@NotNull(groups = BasicInfo.class)
	private StateProvince stateprovince;

	public Address() {
	}
}