package com.icesi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.BusinessEntity;
import com.icesi.repository.BusinessEntityRepository;

public class BusinessEntityService {
	
	private BusinessEntityRepository beRepository;

	@Autowired
	public BusinessEntityService(BusinessEntityRepository beRepository) {
		this.beRepository = beRepository;
	}
	
	public BusinessEntity save(BusinessEntity be) {
		return beRepository.save(be);
	}
	
	public Iterable<BusinessEntity> findAll() {
		return beRepository.findAll();
	}
	
	public void delete(BusinessEntity be) {
		beRepository.delete(be);

	}
	
	public Optional<BusinessEntity> findById(long id) {

		return beRepository.findById(id);
	}

	public void update(BusinessEntity be, long id) {
		BusinessEntity modBe = beRepository.findById(id).get();
		modBe.setName(be.getName());
	
		beRepository.save(modBe);
		
	}
	

}
