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

	// Business Entity
	//
	// ----------------------------------------------------
	//
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
	public ResponseEntity<BusinessEntityAddress> createEntityAddress(
			@Validated(BasicInfo.class) @RequestBody BusinessEntityAddress bea) {
		beaService.save(bea);
		return new ResponseEntity<BusinessEntityAddress>(bea, HttpStatus.CREATED);
	}

	@PutMapping("/baddresses/{id}")
	public ResponseEntity<BusinessEntityAddress> updateEntityAddress(@PathVariable(value = "id") Integer id,
			@Validated(BasicInfo.class) @RequestBody BusinessEntityAddress bea) {

		beaService.update(bea);
		return ResponseEntity.ok(bea);
	}

	// Display Types
	//
	// ----------------------------------------------------
	//
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/phonetypes", method = RequestMethod.GET)
	public ResponseEntity<PhoneNumberType> listPhoneTypes() {
		List<PhoneNumberType> types = ((List<PhoneNumberType>) phoneService.findAllTypes());
		return new ResponseEntity(types, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })

	@RequestMapping(value = "/addresstypes", method = RequestMethod.GET)
	public ResponseEntity<AddressType> listAddressTypes() {
		List<AddressType> types = ((List<AddressType>) adtService.findAll());
		return new ResponseEntity(types, HttpStatus.OK);
	}

	// Persons Phones
	// ---------------------------------------------------------------------------

	@RequestMapping(value = "/phones/{id}", method = RequestMethod.GET)
	public ResponseEntity<Phone> getPhone(@PathVariable(value = "id") Integer id) {
		Phone pp = phoneService.findById(id).get();
		return new ResponseEntity<Phone>(pp, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/phones", method = RequestMethod.GET)
	public ResponseEntity<Phone> listPhones() {
		List<Phone> entities = ((List<Phone>) phoneService.findAll());
		return new ResponseEntity(entities, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/phones/pref={pref}", method = RequestMethod.GET)
	public ResponseEntity<Phone> listPhonesByPreg(@PathVariable(value = "pref") String pref) {
		List<Phone> entities = ((List<Phone>) phoneService.findByPrefix(pref));
		return new ResponseEntity(entities, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/phones/type={type}", method = RequestMethod.GET)
	public ResponseEntity<Phone> listPhonesByTypes(@PathVariable(value = "type") String type) {
		List<Phone> entities = ((List<Phone>) phoneService.findByType(type));
		return new ResponseEntity(entities, HttpStatus.OK);
	}

	@RequestMapping(value = "/phones", method = RequestMethod.POST)
	public ResponseEntity<Phone> createPhone(@Validated(BasicInfo.class) @RequestBody Phone pp) {
		phoneService.save(pp);
		return new ResponseEntity<Phone>(pp, HttpStatus.CREATED);
	}

	@PutMapping("/phones/{id}")
	public ResponseEntity<Phone> updatePhone(@PathVariable(value = "id") Integer id, @Validated(BasicInfo.class) @RequestBody Phone pp) {
		phoneService.update(pp);
		return ResponseEntity.ok(pp);
	}

}
