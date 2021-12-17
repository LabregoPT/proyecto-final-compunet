package com.icesi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class BusinessEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "BUSINESSENTITY_BUSINESSENTITYID_GENERATOR", allocationSize = 1, sequenceName = "BUSINESSENTITY_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESSENTITY_BUSINESSENTITYID_GENERATOR")
	private long businessentityid;
	
	@NotBlank(groups = BasicInfo.class)
	private String name;
	
	private Integer rowguid;

	@OneToMany(mappedBy = "businessentity")
	@JsonIgnore
	private List<BusinessEntityAddress> bea;

	@OneToOne(mappedBy = "businessentity")
	@JsonIgnore
	private Person person;

	public BusinessEntity() {
	}

	public BusinessEntityAddress addBusinessentityaddress(BusinessEntityAddress b) {
		bea.add(b);
		b.setBusinessentity(this);
		return b;
	}

	public BusinessEntityAddress removeBusinessentityaddress(BusinessEntityAddress b) {
		bea.remove(b);
		b.setBusinessentity(null);
		return b;
	}
}