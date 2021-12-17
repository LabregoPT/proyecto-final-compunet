package com.icesi.serviceDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.icesi.DAO.BusinessEntityDAO;
import com.icesi.DAO.PersonDAO;
import com.icesi.model.Person;



@Service
public class PersonService {
	
	private PersonDAO personRepository;
	private BusinessEntityDAO beRepository;

	@Autowired
	public PersonService(PersonDAO personRepository,BusinessEntityDAO beRepository) {
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
	
	public Iterable<Person> findByTitle(String title){
		return personRepository.getByTittle(title);
	}
	
	public Iterable<Person> findByInterval(String date1, String date2) {
		return personRepository.getByDate(date1, date2);
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
	
	
	public Iterable<Person> findByEntityId(long id) {
		return personRepository.getByID(id);
	}
	

}
