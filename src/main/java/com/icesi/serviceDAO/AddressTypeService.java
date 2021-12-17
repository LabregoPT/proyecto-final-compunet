package com.icesi.serviceDAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.AddressTypeDAO;
import com.icesi.model.AddressType;


@Service
public class AddressTypeService {
	
	private AddressTypeDAO atRepository;

	@Autowired
	public AddressTypeService( AddressTypeDAO addressTRepository) {
		this.atRepository = addressTRepository;
	}
	
	public void save(AddressType ad) {
		atRepository.save(ad);
	}
	
	public Iterable<AddressType> findAll() {
		return atRepository.getAll();
	}

	public Optional<AddressType> findById(Integer id) {
		return atRepository.get(id);
	}

	public void delete(AddressType adt) {
		atRepository.delete(adt);
		
	}

	public void update(AddressType adt) {
		AddressType modAd = atRepository.get(adt.getAddresstypeid()).get();
		modAd.setName(adt.getName());
		atRepository.save(modAd);
	}

	
}
