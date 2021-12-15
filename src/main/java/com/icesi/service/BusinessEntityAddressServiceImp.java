package com.icesi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icesi.model.Businessentityaddress;
import com.icesi.repository.AddressRepository;
import com.icesi.repository.AddressTypeRepository;
import com.icesi.repository.BusinessEntityAddressRepository;
import com.icesi.repository.BusinessEntityRepository;

@Service
public class BusinessEntityAddressServiceImp {
	
	private BusinessEntityAddressRepository beaRepository;
	private BusinessEntityRepository beRepository;
	private AddressTypeRepository adtRepository;
	private AddressRepository adRepository;
	
	@Autowired
	public BusinessEntityAddressServiceImp(BusinessEntityAddressRepository beaRepository,BusinessEntityRepository beRepository,AddressTypeRepository adtRepository,
			AddressRepository adRepository) {
		this.beaRepository = beaRepository;
		this.beRepository = beRepository;
		this.adtRepository = adtRepository;
		this.adRepository = adRepository;
	}
	
	
	public Businessentityaddress save(Businessentityaddress bea) {
		bea.setBusinessentity(beRepository.getById(bea.getBusinessentity().getBusinessentityid()));
		bea.setAddresstype(adtRepository.getById(bea.getAddresstype().getAddresstypeid()));
		bea.setAddress(adRepository.getById(bea.getAddress().getAddressid()));
		return beaRepository.save(bea);
	}
	
	public Iterable<Businessentityaddress> findAll(){
		return beaRepository.findAll();
	}


	public Optional<Businessentityaddress> findById(Integer id) {
		return beaRepository.findById(id);
	}


	public void delete(Businessentityaddress bea) {
		beaRepository.delete(bea);	
	}
	
	public void update(Businessentityaddress bea) {
		Businessentityaddress modBea = beaRepository.findById(bea.getId()).get();
		modBea.setBusinessentity(beRepository.getById(bea.getBusinessentity().getBusinessentityid()));
		modBea.setAddresstype(adtRepository.getById(bea.getAddresstype().getAddresstypeid()));
		modBea.setAddress(adRepository.getById(bea.getAddress().getAddressid()));
		
		beaRepository.save(modBea);
		
	}

}
