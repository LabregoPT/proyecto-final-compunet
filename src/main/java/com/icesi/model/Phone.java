package com.icesi.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class Phone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "PHONE_PHONEID_GENERATOR", allocationSize = 1, sequenceName = "PHONE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONE_PHONEID_GENERATOR")
	private Integer id;
		
	private Timestamp modifiedDate;
	
	@NotBlank(groups = BasicInfo.class)
	@Size(min = 10, max = 10)
	private String phonenumber;

	@ManyToOne
	private Person person;
	
	@ManyToOne
	private PhoneNumberType type;

	public Phone() {
	}

}