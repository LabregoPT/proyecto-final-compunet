package com.icesi.serviceDAO;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.model.BusinessEntity;
@Service
public class BusinessEntityService {
	

	private BusinessEntityDAO beRepository;

	@Autowired
	public BusinessEntityService(BusinessEntityDAO beRepository) {
		this.beRepository = beRepository;
	}
	
	
	public void save(BusinessEntity be) {
		beRepository.save(be);
	}
	
	public Iterable<BusinessEntity> findAll() {
		return beRepository.getAll();
	}
	
	public void delete(BusinessEntity be) {
		beRepository.delete(be.getBusinessentityid());

	}
	
	public Optional<BusinessEntity> findById(long id) {

		return beRepository.get(id);
	}

	public void update(BusinessEntity be, long id) {
		BusinessEntity modBe = beRepository.get(id).get();
		modBe.setName(be.getName());
	
		beRepository.update(modBe);
		
	}
	

}
