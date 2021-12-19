package com.icesi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PERSON_PERSONID_GENERATOR", allocationSize = 1, sequenceName = "PERSON_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_PERSONID_GENERATOR")
	private long personid;
	
	@NotBlank(groups = EditInfo.class)
	private String additionalcontactinfo;
	
	@NotBlank(groups = EditInfo.class)
	private String demographics;
	
	@NotNull(groups = EditInfo.class)
	private Integer emailpromotion;
	
	@Size(min = 3, max = 20, groups = BasicInfo.class)
	private String firstname;
	
	@Size(min = 3, max = 20, groups = BasicInfo.class)
	private String lastname;
	
	@NotBlank(groups = EditInfo.class)
	private String middlename;
	
	@NotNull(groups = BasicInfo.class)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;
	
	@NotBlank(groups = EditInfo.class)
	private String namestyle;
	
	@NotBlank(groups = EditInfo.class)
	private String persontype;
	
	@NotNull(groups = EditInfo.class)
	private Integer rowguid;
	
	@NotBlank(groups = EditInfo.class)
	private String suffix;
	
	@NotBlank(groups = EditInfo.class)
	private String title;

	@OneToOne
	@NotNull(groups = BasicInfo.class)
	@JsonIgnore
	private BusinessEntity businessentity;
	
	@OneToMany(mappedBy = "person")
	@JsonIgnore
	private List<Phone> phones;

	public Person() {
	}

	public Phone addPhone(Phone phone) {
		phones.add(phone);
		return phone;
	}
	public Phone removePhone(Phone phone) {
		phones.remove(phone);
		phone.setPerson(null);
		return phone;
	}
}