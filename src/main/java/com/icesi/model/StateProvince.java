package com.icesi.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class StateProvince implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "STATEPROVINCE_STATEPROVINCEID_GENERATOR", allocationSize = 1, sequenceName = "STATEPROVINCE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STATEPROVINCE_STATEPROVINCEID_GENERATOR")
	private Integer stateprovinceid;

	private Timestamp modifieddate;
	
	@NotBlank(groups = BasicInfo.class)
	private String name;

	private Integer rowguid;

	@OneToMany(mappedBy = "stateprovince")
	@JsonIgnore
	private List<Address> addresses;

	public StateProvince() {
	}

	public Address addAddress(Address a) {
		addresses.add(a);
		a.setStateprovince(this);
		return a;
	}


	public Address removeAddress(Address a) {
		addresses.remove(a);
		a.setStateprovince(null);
		return a;
	}


}