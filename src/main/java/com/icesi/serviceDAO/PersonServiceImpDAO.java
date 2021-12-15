package com.icesi.serviceDAO;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.DAO.PersonDAO;
import com.icesi.model.Person;



@Service
public class PersonServiceImpDAO {
	
	private PersonDAO personRepository;
	private BusinessEntityDAO beRepository;

	@Autowired
	public PersonServiceImpDAO(PersonDAO personRepository,BusinessEntityDAO beRepository) {
		this.personRepository = personRepository;
		this.beRepository = beRepository;
	}
	
	public void save(Person person) {		
		person.setBusinessentity(beRepository.get(person.getBusinessentity().getBusinessentityid()).get());
		personRepository.save(person);
	}
	
	public Iterable<Person> findAll() {
		return personRepository.getAll();
	}
	
	public void delete(Person person) {
		personRepository.delete(person.getPersonid());

	}
	
	
	
	public Person findById(long id) {

		return personRepository.get(id).get();
	}
	
	
	public void update(Person person) {
		Person modPerson = personRepository.get(person.getPersonid()).get();
		modPerson.setFirstname(person.getFirstname());
		modPerson.setLastname(person.getLastname());
		modPerson.setTitle(person.getTitle());
		modPerson.setBusinessentity(beRepository.get(person.getBusinessentity().getBusinessentityid()).get());

		personRepository.update(modPerson);
	}
	
	
	public Iterable<Person> findAllById(long id) {
		ArrayList<Person> persons = (ArrayList<Person>) personRepository.getAll();
		String name = beRepository.get(id).get().getName();
		ArrayList<Person> toReturn = new ArrayList<Person>();
		
		for (Person person : persons) {
			if(person.getBusinessentity().getName().equals(name)) {
				toReturn.add(person);
			}
		}
		return toReturn;
	}
	

}