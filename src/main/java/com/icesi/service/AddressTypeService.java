package com.icesi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.AddressType;
import com.icesi.repository.AddressTypeRepository;

public class AddressTypeService {
	
	private AddressTypeRepository addressTRepository;

	@Autowired
	public AddressTypeService( AddressTypeRepository addressTRepository) {
		this.addressTRepository = addressTRepository;
	}
	
	public AddressType save(AddressType ad) {
		return addressTRepository.save(ad);
	}
	
	public Iterable<AddressType> findAll() {
		return addressTRepository.findAll();
	}

	public Optional<AddressType> findById(Integer id) {
		return addressTRepository.findById(id);
	}

	public void delete(AddressType adt) {
		addressTRepository.delete(adt);
		
	}

	public void update(AddressType adt) {
		AddressType modAd = addressTRepository.findById(adt.getAddresstypeid()).get();
		modAd.setName(adt.getName());
		
		
		
		
		addressTRepository.save(modAd);
		
	}

	
}
