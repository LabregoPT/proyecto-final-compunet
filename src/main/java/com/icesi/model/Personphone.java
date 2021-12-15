package com.icesi.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * The persistent class for the personphone database table.
 *
 */
@Entity
@NamedQuery(name = "Personphone.findAll", query = "SELECT p FROM Personphone p")
public class Personphone implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "PERSONPHONE_PERSONPHONE_GENERATOR", allocationSize = 1, sequenceName = "PERSONPHONE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONPHONE_PERSONPHONEID_GENERATOR")
	private Integer id;
	

	private Timestamp modifieddate;

	// bi-directional many-to-one association to Person
	@ManyToOne
	//@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	private Person person;

	// bi-directional many-to-one association to Phonenumbertype
	@ManyToOne
	//@JoinColumn(name = "phonenumbertypeid", insertable = false, updatable = false)
	private Phonenumbertype phonenumbertype;
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	private String phone;

	public Personphone() {
	}



	public Timestamp getModifieddate() {
		return this.modifieddate;
	}

	public Person getPerson() {
		return this.person;
	}

	public Phonenumbertype getPhonenumbertype() {
		return this.phonenumbertype;
	}

	

	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setPhonenumbertype(Phonenumbertype phonenumbertype) {
		this.phonenumbertype = phonenumbertype;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}

}