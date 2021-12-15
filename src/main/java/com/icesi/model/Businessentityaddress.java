package com.icesi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;

/**
 * The persistent class for the businessentityaddress database table.
 *
 */
@Entity
@NamedQuery(name = "Businessentityaddress.findAll", query = "SELECT b FROM Businessentityaddress b")
public class Businessentityaddress implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "BUSINESSENTITYADDRESS_BUSINESSENTITYADDRESSID_GENERATOR", allocationSize = 1, sequenceName = "BUSINESSENTITYADDRESS_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESSENTITYADDRESS_BUSINESSENTITYADDRESSID_GENERATOR")
	private Integer id;

	private LocalDate modifieddate;

	private Integer rowguid;

	// bi-directional many-to-one association to Address
	@ManyToOne
	//@JoinColumn(name = "addressid", insertable = false, updatable = false)
	private Address address;

	// bi-directional many-to-one association to Addresstype
	@ManyToOne
	//@JoinColumn(name = "addresstypeid", insertable = false, updatable = false)
	private Addresstype addresstype;

	// bi-directional many-to-one association to Businessentity
	@ManyToOne
	//@JoinColumn(name = "businessentityid", insertable = false, updatable = false)
	private Businessentity businessentity;
	
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

	public Businessentityaddress() {
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public Address getAddress() {
		return this.address;
	}

	public Addresstype getAddresstype() {
		return this.addresstype;
	}

	public Businessentity getBusinessentity() {
		return this.businessentity;
	}

	public Integer getId() {
		return this.id;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAddresstype(Addresstype addresstype) {
		this.addresstype = addresstype;
	}

	public void setBusinessentity(Businessentity businessentity) {
		this.businessentity = businessentity;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public String getNameb() {
		return nameb;
	}

	public void setNameb(String nameb) {
		this.nameb = nameb;
	}

	public String getNamea() {
		return namea;
	}

	public void setNamea(String namea) {
		this.namea = namea;
	}

}