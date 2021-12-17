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

import com.icesi.model.*;
import com.icesi.serviceDAO.*;

public class UserRestController {

	private BusinessEntityAddressService beaService;
	private PhoneService phoneService;
	private AddressTypeService adtService;
	
	
	@Autowired
	public UserRestController(BusinessEntityAddressService beaService, AddressTypeService adtService,
			PhoneService phoneService) {
		this.beaService = beaService;
		this.adtService = adtService;
		this.phoneService = phoneService;
	}
	
	//BUSINESS ENTITY ADDRESS
	//----------------------------------------------------------------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/baddresses", method = RequestMethod.GET)
	public ResponseEntity<BusinessEntityAddress> listEntitiesAddresses() {
		List<BusinessEntityAddress> entities = ((List<BusinessEntityAddress>) beaService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/baddresses/{id}", method = RequestMethod.GET)
	public ResponseEntity<BusinessEntityAddress> getEntityAddress(@PathVariable(value = "id") Integer id) {
		BusinessEntityAddress bea = beaService.findById(id).get();
		return new ResponseEntity<BusinessEntityAddress>(bea, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/baddresses", method = RequestMethod.POST)
	public ResponseEntity<BusinessEntityAddress> createEntityAddress(@Validated(BasicInfo.class) @RequestBody BusinessEntityAddress bea) {
		beaService.save(bea);
		return new ResponseEntity<BusinessEntityAddress>(bea, HttpStatus.CREATED);
	}
	
	@PutMapping("/baddresses/{id}")
	public ResponseEntity<BusinessEntityAddress> updateEntityAddress(@PathVariable(value = "id") Integer id,
			@Validated(BasicInfo.class) @RequestBody BusinessEntityAddress bea) {

		beaService.update(bea);
		return ResponseEntity.ok(bea);
	}
	
	/*
	
	//AUX TYPES
	//----------------------------------------------------------------------------------------------
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/phonetypes", method = RequestMethod.GET)
	public ResponseEntity<Phonenumbertype> listPhoneTypes() {
		List<Phonenumbertype> types = ((List<Phonenumbertype>)pntService.findAll());
		return new ResponseEntity(types, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/addresstypes", method = RequestMethod.GET)
	public ResponseEntity<Addresstype> listAddressTypes() {
		List<Addresstype> types = ((List<Addresstype>)adtService.findAll());
		return new ResponseEntity(types, HttpStatus.OK);
	}
	
	//PERSON PHONE
	//----------------------------------------------------------------------------------------------
	
	@RequestMapping(value = "/phones/{id}", method = RequestMethod.GET)
	public ResponseEntity<Personphone> getPhone(@PathVariable(value = "id") Integer id) {
		Personphone pp = phoneService.findById(id).get();
		return new ResponseEntity<Personphone>(pp, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/phones", method = RequestMethod.GET)
	public ResponseEntity<Personphone> listPhones() {
		List<Personphone> entities = ((List<Personphone>) phoneService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/phones/pref={pref}", method = RequestMethod.GET)
	public ResponseEntity<Personphone> listPhonesByPreg(@PathVariable(value = "pref") String pref) {
		List<Personphone> entities = ((List<Personphone>) phoneService.findByPref(pref));
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/phones/list", method = RequestMethod.GET)
	public ResponseEntity<Personphone> listPhonesByTypes() {
		List<Personphone> entities = ((List<Personphone>) phoneService.findAllTypes());
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/phones/type={type}", method = RequestMethod.GET)
	public ResponseEntity<Personphone> listPhonesByTypes(@PathVariable(value = "type") String type) {
		List<Personphone> entities = ((List<Personphone>) phoneService.findByType(type));
		return new ResponseEntity(entities, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/phones", method = RequestMethod.POST)
	public ResponseEntity<Personphone> createPhone(@Validated(BasicInfo.class) @RequestBody Personphone pp) {
		phoneService.save(pp);
		return new ResponseEntity<Personphone>(pp, HttpStatus.CREATED);
	}
	
	@PutMapping("/phones/{id}")
	public ResponseEntity<Personphone> updatePhone(@PathVariable(value = "id") Integer id,
			@Validated(BasicInfo.class) @RequestBody Personphone pp) {

		phoneService.update(pp);
		return ResponseEntity.ok(pp);
	}
	
*/
	
}
