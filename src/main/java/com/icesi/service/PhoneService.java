package com.icesi.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icesi.model.Phone;
import com.icesi.repository.PhoneRepository;

public class PhoneService {

	private PhoneRepository phoneRepository;

	@Autowired
	public PhoneService(PhoneRepository phoneRepository) {
		this.phoneRepository = phoneRepository;
	}

	public Optional<Phone> findById(Integer id) {

		return phoneRepository.findById(id);
	}

	public void delete(Phone phone) {
		phoneRepository.delete(phone);
	}

	public Iterable<Phone> findAll() {
		return phoneRepository.findAll();
	}

	public void save(Phone phone) {
		phoneRepository.save(phone);

	}

	public void update(Phone phone) {
		Phone modPhone = phoneRepository.getById(phone.getId());
		modPhone.setPerson(phone.getPerson());
		modPhone.setType(phone.getType());
		modPhone.setPhonenumber(phone.getPhonenumber());
		phoneRepository.save(phone);
	}

}
