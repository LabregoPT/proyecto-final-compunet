package com.icesi.serviceDAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.*;
import com.icesi.model.BusinessEntityAddress;

@Service
public class BusinessEntityAddressService {
	
	private BusinessEntityAddressDAO beaRepository;
	private BusinessEntityDAO beRepository;
	private AddressTypeDAO adtRepository;
	private AddressDAO adRepository;
	
	@Autowired
	public BusinessEntityAddressService(BusinessEntityAddressDAO beaRepository,BusinessEntityDAO beRepository,AddressTypeDAO adtRepository,
			AddressDAO adRepository) {
		this.beaRepository = beaRepository;
		this.beRepository = beRepository;
		this.adtRepository = adtRepository;
		this.adRepository = adRepository;
	}
	
	public void save(BusinessEntityAddress bea) {
		bea.setBusinessentity(beRepository.get(bea.getBusinessentity().getBusinessentityid()).get());
		bea.setAddresstype(adtRepository.get(bea.getBusinessentity().getBusinessentityid()).get());
		bea.setAddress(adRepository.get(bea.getAddress().getAddressid()).get());
		beaRepository.save(bea);
	}
	
	public Iterable<BusinessEntityAddress> findAll(){
		return beaRepository.getAll();
	}


	public Optional<BusinessEntityAddress> findById(Integer id) {
		return beaRepository.get(id);
	}


	public void delete(BusinessEntityAddress bea) {
		beaRepository.delete(bea);	
	}
	
	public void update(BusinessEntityAddress bea) {
		BusinessEntityAddress modBea = beaRepository.get(bea.getId()).get();
		modBea.setBusinessentity(beRepository.get(bea.getBusinessentity().getBusinessentityid()).get());
		modBea.setAddresstype(adtRepository.get(bea.getAddresstype().getAddresstypeid()).get());
		modBea.setAddress(adRepository.get(bea.getAddress().getAddressid()).get());
		beaRepository.save(modBea);
	}
}
