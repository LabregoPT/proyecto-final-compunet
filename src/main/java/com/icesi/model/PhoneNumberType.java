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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class PhoneNumberType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PHONENUMBERTYPE_PHONENUMBERTYPEID_GENERATOR", allocationSize = 1, sequenceName = "PHONENUMBERTYPE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONENUMBERTYPE_PHONENUMBERTYPEID_GENERATOR")
	private Integer phonenumbertypeid;

	private Timestamp modifieddate;

	private String name;

	@OneToMany(mappedBy = "type")
	@JsonIgnore
	private List<Phone> phones;

	public PhoneNumberType() {
	}

	public Phone addPhone(Phone p) {
		phones.add(p);
		p.setType(this);
		return p;
	}
	public Phone removePersonphone(Phone p) {
		phones.remove(p);
		p.setType(null);
		return p;
	}

}