package com.icesi.integration.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.DAO.*;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class PhoneDAOTest{
	
	@Autowired
	private PhoneDAO phoneRepository;
	
	@Autowired
	private PersonDAO personRepository;
	
	@Autowired
	private PhoneNumberTypeDAO pntRepository;
	
	public void pre() {
		Phone p = new Phone();
		Person pe = new Person();
		pe.setFirstname("Juan");
		pe.setLastname("David");
		pe.setTitle("Manager");
		pe.setModifieddate(LocalDate.now());
		personRepository.save(pe);
		
		p.setPerson(pe);
		p.setType(pntRepository.get((Integer) 1).get());
		p.setPhonenumber("3101000000");
	}
	
	@Test
	@Order(1)
	public void savePhone() {
		Phone p = new Phone();
		Person pe = new Person();
		pe.setFirstname("Juan");
		pe.setLastname("David");
		pe.setTitle("Manager");
		pe.setModifieddate(LocalDate.now());
		personRepository.save(pe);
		
		p.setPerson(pe);
		p.setType(pntRepository.get((Integer) 1).get());
		p.setPhonenumber("3101000000");
		phoneRepository.save(p);
		List<Phone> phones = phoneRepository.getAll(); 
		
		assertEquals(phones.size(), 2);
	}
	
	
	@Test
	@Order(2)
	public void findByIdSP() {
		Phone pSaved = phoneRepository.get((Integer) 500).orElse(null);
		assertNull(pSaved);
	}
	
	@Test
	@Order(3)
	public void getAll() {
		List<Phone> phones = phoneRepository.getAll(); 
		
		assertEquals(phones.size(), 2);
	}

	
	@Test
	@Order(4)
	public void shouldUpdateBusinessentity() {
		pre();
		Phone pp = phoneRepository.get((Integer) 1).get();
		pp.setPhonenumber("3101000003");
		phoneRepository.update(pp);
		String modNumber = phoneRepository.get((Integer) 1).get().getPhonenumber();
		
		assertEquals(modNumber, "3101000003");
	}

}
