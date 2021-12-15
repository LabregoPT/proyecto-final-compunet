package com.icesi.serviceDAO;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.model.Businessentity;
@Service
public class BusinessEntityServiceImpDAO {
	

	private BusinessEntityDAO beRepository;

	@Autowired
	public BusinessEntityServiceImpDAO(BusinessEntityDAO beRepository) {
		this.beRepository = beRepository;
	}
	
	
	public void save(Businessentity be) {
		beRepository.save(be);
	}
	
	public Iterable<Businessentity> findAll() {
		return beRepository.getAll();
	}
	
	public void delete(Businessentity be) {
		beRepository.delete(be.getBusinessentityid());

	}
	
	public Optional<Businessentity> findById(long id) {

		return beRepository.get(id);
	}

	public void update(Businessentity be, long id) {
		Businessentity modBe = beRepository.get(id).get();
		modBe.setName(be.getName());
	
		beRepository.update(modBe);
		
	}
	

}
