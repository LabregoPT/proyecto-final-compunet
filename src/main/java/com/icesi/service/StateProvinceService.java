package com.icesi.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.model.Address;

import com.icesi.model.StateProvince;
import com.icesi.repository.StateProvinceRepository;

public class StateProvinceService {
	
	
	private StateProvinceRepository spRepository;

	@Autowired
	public StateProvinceService(StateProvinceRepository spRepository) {
		this.spRepository = spRepository;
	}
	
	public StateProvince save(StateProvince sp) {
		return spRepository.save(sp);
	}
	
	public Iterable<StateProvince> findAll() {
		return spRepository.findAll();
	}
	
	
	public Iterable<Address> findAddresses(Integer id){
		return spRepository.getById(id).getAddresses();
	}
	
	public void delete(StateProvince sp) {
		spRepository.delete(sp);

	}
	
	public void deleteAll() {
		spRepository.deleteAll();
	}
	
	public Optional<StateProvince> findById(Integer id) {

		return spRepository.findById(id);
	}
	
	public void update(StateProvince sp, Integer id) {
		StateProvince modSp = spRepository.findById(id).get();
		modSp.setName(sp.getName());
	
		spRepository.save(modSp);
	}
	
	
}
