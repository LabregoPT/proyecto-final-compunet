package com.icesi.serviceDAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.icesi.model.Personphone;
import com.icesi.repository.PersonPhoneRepository;

public class PhoneService {

	   
    private PersonPhoneRepository phoneRepository;
    
    
    @Autowired
    public PhoneService(PersonPhoneRepository phoneRepository) {
    	this.phoneRepository = phoneRepository;
    }
    
   
    public Optional<Personphone> findById(Integer id) {

		return phoneRepository.findById(id);
	}
    
    public void delete(Personphone phone) {
		phoneRepository.delete(phone);
	}
    
    public Iterable<Personphone> findAll(){
    	return phoneRepository.findAll();
    }


	public void save(Personphone phone) {
		phoneRepository.save(phone);
		
	}


	public void update(Personphone phone) {
		Personphone modPhone = phoneRepository.getById(phone.getId());
		modPhone.setPerson(phone.getPerson());
		modPhone.setPhonenumbertype(phone.getPhonenumbertype());
		modPhone.setPhone(phone.getPhone());

		phoneRepository.save(phone);
		
	}
    	
    	
}
