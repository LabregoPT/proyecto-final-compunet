package com.icesi.serviceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.*;
import com.icesi.model.Address;
import com.icesi.model.StateProvince;

@Service
public class AddressService {

	// Repositories
	public AddressDAO addressRepo;
	public StateProvinceDAO stateRepo;

	// Methods --------------------------------------------
	@Autowired
	public AddressService(AddressDAO addressRepo, StateProvinceDAO stateRepo) {
		this.addressRepo = addressRepo;
		this.stateRepo = stateRepo;
	}

	// Save
	public void save(Address a) {
		StateProvince sp = stateRepo.get(a.getStateprovince().getStateprovinceid()).get();
		a.setStateprovince(sp);
		addressRepo.save(a);
	}

	// Find All
	public Iterable<Address> findAll() {
		return addressRepo.getAll();
	}

	// Find by ID
	public Address findById(Integer id) {
		return addressRepo.get(id).get();
	}

	// Find by Date
	public Iterable<Address> findByDate(String date) {
		return addressRepo.findByDate(date);
	}

	// Update
	public void update(Address ad) {
		Address modAd = addressRepo.get(ad.getAddressid()).get();
		modAd.setAddressline1(ad.getAddressline1());
		modAd.setCity(ad.getCity());
		modAd.setPostalcode(ad.getPostalcode());
		modAd.setStateprovince(stateRepo.get(ad.getStateprovince().getStateprovinceid()).get());
		addressRepo.update(modAd);
	}

	public Iterable<Address> findAllInId(Integer id) {
		return stateRepo.get(id).get().getAddresses();
	}

}
