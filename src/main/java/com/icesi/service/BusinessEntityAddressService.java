package com.icesi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icesi.model.BusinessEntityAddress;
import com.icesi.repository.AddressRepository;
import com.icesi.repository.AddressTypeRepository;
import com.icesi.repository.BusinessEntityAddressRepository;
import com.icesi.repository.BusinessEntityRepository;

public class BusinessEntityAddressService {
	
	private BusinessEntityAddressRepository beaRepository;
	private BusinessEntityRepository beRepository;
	private AddressTypeRepository adtRepository;
	private AddressRepository adRepository;
	
	@Autowired
	public BusinessEntityAddressService(BusinessEntityAddressRepository beaRepository,BusinessEntityRepository beRepository,AddressTypeRepository adtRepository,
			AddressRepository adRepository) {
		this.beaRepository = beaRepository;
		this.beRepository = beRepository;
		this.adtRepository = adtRepository;
		this.adRepository = adRepository;
	}
	
	
	public BusinessEntityAddress save(BusinessEntityAddress bea) {
		bea.setBusinessentity(beRepository.getById(bea.getBusinessentity().getBusinessentityid()));
		bea.setAddresstype(adtRepository.getById(bea.getAddresstype().getAddresstypeid()));
		bea.setAddress(adRepository.getById(bea.getAddress().getAddressid()));
		return beaRepository.save(bea);
	}
	
	public Iterable<BusinessEntityAddress> findAll(){
		return beaRepository.findAll();
	}


	public Optional<BusinessEntityAddress> findById(Integer id) {
		return beaRepository.findById(id);
	}


	public void delete(BusinessEntityAddress bea) {
		beaRepository.delete(bea);	
	}
	
	public void update(BusinessEntityAddress bea) {
		BusinessEntityAddress modBea = beaRepository.findById(bea.getId()).get();
		modBea.setBusinessentity(beRepository.getById(bea.getBusinessentity().getBusinessentityid()));
		modBea.setAddresstype(adtRepository.getById(bea.getAddresstype().getAddresstypeid()));
		modBea.setAddress(adRepository.getById(bea.getAddress().getAddressid()));
		
		beaRepository.save(modBea);
		
	}

}
