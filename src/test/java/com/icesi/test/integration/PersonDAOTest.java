package com.icesi.test.integration;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import com.icesi.DAO.*;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class PersonDAOTest {
	
	@Autowired
	private PersonDAO personRepository;
	
	@Autowired
	private BusinessEntityDAO beRepository;
	
	
	@Test
	@Order(1)
	public void savePerson() {
		BusinessEntity be = new BusinessEntity();
		be.setName("Entidad");
		beRepository.save(be);
		Person pe2 = new Person();
		pe2.setBusinessentity(beRepository.get(2).get());
		pe2.setFirstname("Juan");
		pe2.setLastname("David");
		pe2.setTitle("Manager");
		pe2.setModifieddate(LocalDate.now());
		personRepository.save(pe2);
		List<Person> persons = personRepository.getAll(); 
		
		assertEquals(persons.size(), 2);
	}
	
	
	@Test
	@Order(2)
	public void findByIDPerson() {
		Person peSaved = personRepository.get(500).orElse(null);
		assertNull(peSaved);
	}
	
	@Test
	@Order(3)
	public void getAllPersons() {
		List<Person> persons = personRepository.getAll(); 
		
		assertEquals(persons.size(), 1);
	}

	
	@Test
	@Order(4)
	public void updatePerson() {
		Person pe = personRepository.get(1).get();
		pe.setFirstname("Mario Hugo");
		personRepository.update(pe);
		
		String moddedName = personRepository.get(1).get().getFirstname();
		assertEquals(moddedName, "Mario Hugo");
	}
	
	
	@Test
	@Order(5)
	public void searchByTitle() {
		String r = "Titulo";
		List<Person> persons = personRepository.getByTittle(r);
		assertEquals(persons.size(), 1);
	}
	
	@Test
	@Order(6)
	public void searchByInterval() {
		List<Person> persons = personRepository.getByDate("2021-11-28", "2021-11-30");
		assertEquals(persons.size(), 0);
	}

}
