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

import lombok.Data;

@Data
@Entity
public class AddressType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ADDRESSTYPE_ADDRESSTYPEID_GENERATOR", allocationSize = 1, sequenceName = "ADDRESSTYPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESSTYPE_ADDRESSTYPEID_GENERATOR")
	private Integer addresstypeid;

	private Timestamp modifieddate;
	
	@NotBlank(groups = BasicInfo.class)
	private String name;

	private Integer rowguid;

	@OneToMany(mappedBy = "addresstype")
	private List<BusinessEntityAddress> bea;

	public AddressType() {
	}

	public BusinessEntityAddress addBusinessEntityAddress(BusinessEntityAddress b) {
		bea.add(b);
		b.setAddresstype(this);
		return b;
	}

	public BusinessEntityAddress removeBusinessEntityAddress(BusinessEntityAddress b) {
		bea.remove(b);
		b.setAddresstype(null);
		return b;
	}

}