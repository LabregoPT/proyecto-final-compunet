package com.icesi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.PhoneNumberType;
import com.icesi.repository.PhoneNumberTypeRepository;

public class PhoneNumbertypeService {
	
	
	private PhoneNumberTypeRepository pntRepository;
	
	@Autowired
	public PhoneNumbertypeService(PhoneNumberTypeRepository pntRepository) {
		this.pntRepository = pntRepository;
	}
	
	
	public Iterable<PhoneNumberType> findAll() {
		return pntRepository.findAll();
	}
		
}
