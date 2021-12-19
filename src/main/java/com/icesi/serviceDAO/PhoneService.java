package com.icesi.serviceDAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.*;
import com.icesi.model.*;


@Service
public class PhoneService {

	   
    private PhoneDAO phoneRepository;
    private PhoneNumberTypeDAO pntRepo;
    
    
    @Autowired
    public PhoneService(PhoneDAO phoneRepository, PhoneNumberTypeDAO pntRepo) {
    	this.phoneRepository = phoneRepository;
    	this.pntRepo = pntRepo;
    }
    
   
    public Optional<Phone> findById(Integer id) {
    	return phoneRepository.get(id);
    }
    
    public void delete(Phone phone) {
		phoneRepository.delete(phone);
	}
    
    public Iterable<Phone> findAll(){
    	return phoneRepository.getAll();
    }

    public Iterable<PhoneNumberType> findAllTypes(){
    	return pntRepo.getAll();
    }
    
    public Iterable<Phone> findByPrefix(String prefix){
    	return phoneRepository.getByPrefix(prefix);
    }
    
    public Iterable<Phone> findByType(String type){
    	return phoneRepository.getByType(type);
    }

	public void save(Phone phone) {
		phoneRepository.save(phone);
		checkAddingPT(phone);
	}


	public void update(Phone phone) {
		Phone modPhone = phoneRepository.get(phone.getId()).get();
		modPhone.setPerson(phone.getPerson());
		modPhone.setType(phone.getType());
		modPhone.setPhonenumber(phone.getPhonenumber());
		phoneRepository.update(phone);
		checkAddingPT(phone);
	}
	
	private void checkAddingPT(Phone phone) {
		List<PhoneNumberType> current = pntRepo.getAll();
		boolean add = true;
		for(PhoneNumberType t:current) {
			if(t.getPhonenumbertypeid() == phone.getType().getPhonenumbertypeid()) {
				add = false;
			}
		}
		if(add) {
			pntRepo.save(phone.getType());
		}
	}
    	
    	
}
