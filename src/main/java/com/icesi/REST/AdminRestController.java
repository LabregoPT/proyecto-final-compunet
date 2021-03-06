package com.icesi.REST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.icesi.model.*;
import com.icesi.serviceDAO.*;

@RestController
public class AdminRestController {

	// Service relations
	private BusinessEntityService beService;
	private AddressService addressService;
	private PersonService personService;
	private StateProvinceService spService;

	@Autowired
	public AdminRestController(PersonService personService, BusinessEntityService beService, AddressService adService,
			StateProvinceService spService) {
		this.personService = personService;
		this.beService = beService;
		this.addressService = adService;
		this.spService = spService;
	}


	// Business Entities
	//
	// ----------------------------------------------------
	//
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/entities", method = RequestMethod.GET)
	public ResponseEntity<BusinessEntity> listEntities() {
		List<BusinessEntity> entities = ((List<BusinessEntity>) beService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}

	@RequestMapping(value = "/entities", method = RequestMethod.POST)
	public ResponseEntity<BusinessEntity> createEntity(@Validated(BasicInfo.class) @RequestBody BusinessEntity be) {
		beService.save(be);
		return new ResponseEntity<BusinessEntity>(be, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/entities/{id}", method = RequestMethod.GET)
	public ResponseEntity<BusinessEntity> getEntity(@PathVariable(value = "id") long id) {
		BusinessEntity be = beService.findById(id).get();
		return new ResponseEntity<BusinessEntity>(be, HttpStatus.OK);
	}

	@PutMapping("/entities/{id}")
	public ResponseEntity<BusinessEntity> updateEntity(@PathVariable(value = "id") long id,
			@Validated(BasicInfo.class) @RequestBody BusinessEntity be) {

		beService.update(be, id);
		return ResponseEntity.ok(be);
	}

	// Addresses
	//
	// ----------------------------------------------------
	//

	@RequestMapping(value = "/addresses/{id}", method = RequestMethod.GET)
	public ResponseEntity<Address> getAddress(@PathVariable(value = "id") Integer id) {
		Address ad = addressService.findById(id);
		return new ResponseEntity<Address>(ad, HttpStatus.OK);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addresses", method = RequestMethod.GET)
	public ResponseEntity<Address> listAddresses() {
		List<Address> addresses = ((List<Address>) addressService.findAll());
		return new ResponseEntity(addresses, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addresses/filter={id}", method = RequestMethod.GET)
	public ResponseEntity<Address> listAddressesByID(@PathVariable(value = "id") Integer id) {
		List<Address> addresses = ((List<Address>) addressService.findAllInId(id));
		return new ResponseEntity(addresses, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addresses/date={date}", method = RequestMethod.GET)
	public ResponseEntity<Address> listAddressesByID(@PathVariable(value = "date") String date) {
		List<Address> addresses = ((List<Address>) addressService.findByDate(date));
		return new ResponseEntity(addresses, HttpStatus.OK);
	}

	@RequestMapping(value = "/addresses", method = RequestMethod.POST)
	public ResponseEntity<Address> createAddress(@Validated(BasicInfo.class) @RequestBody Address ad) {
		addressService.save(ad);
		return new ResponseEntity<Address>(ad, HttpStatus.CREATED);
	}

	@PutMapping("/addresses/{id}")
	public ResponseEntity<Address> updateAddress(@Validated(BasicInfo.class) @RequestBody Address ad) {

		addressService.update(ad);
		return ResponseEntity.ok(ad);
	}

	// Persons
	//
	// ----------------------------------------------------
	//

	@RequestMapping(value = "/persons/{id}", method = RequestMethod.GET)
	public ResponseEntity<Person> getPerson(@PathVariable(value = "id") long id) {
		Person pe = personService.findById(id);
		return new ResponseEntity<Person>(pe, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersons() {
		List<Person> persons = ((List<Person>) personService.findAll());
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/persons/filter={id}", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersonsByID(@PathVariable(value = "id") long id) {
		List<Person> persons = ((List<Person>) personService.findByEntityId(id));
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/persons/title={title}", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersonsByTitle(@PathVariable(value = "title") String title) {
		List<Person> persons = ((List<Person>) personService.findByTitle(title));
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/persons/date1={date1}/date2={date2}", method = RequestMethod.GET)
	public ResponseEntity<Person> listPersonsByDate(@PathVariable(value = "date1") String date1,
			@PathVariable(value = "date2") String date2) {
		List<Person> persons = ((List<Person>) personService.findByInterval(date1, date2));
		return new ResponseEntity(persons, HttpStatus.OK);
	}

	@RequestMapping(value = "/persons", method = RequestMethod.POST)
	public ResponseEntity<Person> createPerson(@Validated(BasicInfo.class) @RequestBody Person pe) {
		personService.save(pe);
		return new ResponseEntity<Person>(pe, HttpStatus.CREATED);
	}

	@PutMapping("/persons/{id}")
	public ResponseEntity<Person> updatePerson(@Validated(BasicInfo.class) @RequestBody Person pe) {

		personService.update(pe);
		return ResponseEntity.ok(pe);
	}

	// States & Provinces
	//
	// ----------------------------------------------------
	//

	@RequestMapping(value = "/states/{id}", method = RequestMethod.GET)
	public ResponseEntity<StateProvince> getState(@PathVariable(value = "id") Integer id) {
		StateProvince sp = spService.findById(id).get();
		return new ResponseEntity<StateProvince>(sp, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/states", method = RequestMethod.GET)
	public ResponseEntity<StateProvince> listStates() {
		List<StateProvince> states = ((List<StateProvince>) spService.findAll());
		return new ResponseEntity(states, HttpStatus.OK);
	}

	@RequestMapping(value = "/states", method = RequestMethod.POST)
	public ResponseEntity<StateProvince> createState(@Validated(BasicInfo.class) @RequestBody StateProvince sp) {
		spService.save(sp);
		return new ResponseEntity<StateProvince>(sp, HttpStatus.CREATED);
	}

	@PutMapping("/states/{id}")
	public ResponseEntity<StateProvince> updateState(@PathVariable(value = "id") Integer id,
			@Validated(BasicInfo.class) @RequestBody StateProvince sp) {
		spService.update(sp, id);
		return ResponseEntity.ok(sp);
	}

}
