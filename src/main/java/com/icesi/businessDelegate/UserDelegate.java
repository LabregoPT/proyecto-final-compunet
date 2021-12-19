package com.icesi.businessDelegate;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import com.icesi.model.*;

@Component
public class UserDelegate {

	private static final String BASE_URL = "http://localhost:8080/";

	@Autowired
	private RestTemplate template;

	// Business Entities Addresses
	//
	// ----------------------------------------------------
	//
	public BusinessEntityAddress getEntity(Integer id) {
		String url = BASE_URL + "baddresses/" + id;
		BusinessEntityAddress bea = template.getForObject(url, BusinessEntityAddress.class);
		return bea;
	}

	public Iterable<BusinessEntityAddress> getAllAdresses() {
		String url = BASE_URL + "baddresses";
		BusinessEntityAddress[] bea = template.getForObject(url, BusinessEntityAddress[].class);
		return Arrays.asList(bea);
	}

	public String createEntityAddress(BusinessEntityAddress bea) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<BusinessEntityAddress> entity = new HttpEntity<BusinessEntityAddress>(bea, headers);
		
		return template.exchange(
				BASE_URL + "baddresses", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updateEntity(Integer id, @Validated(BasicInfo.class) BusinessEntityAddress bea) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<BusinessEntityAddress> entity = new HttpEntity<BusinessEntityAddress>(bea, headers);

		return template.exchange(BASE_URL + "baddresses/" + id, HttpMethod.PUT, entity, String.class).getBody();
	}

	// Phone Number & Address Types
	//
	// ----------------------------------------------------
	//
	public Iterable<PhoneNumberType> getAllPhoneTypes() {
		String url = BASE_URL + "phonetypes";
		PhoneNumberType[] pnt = template.getForObject(url, PhoneNumberType[].class);
		return Arrays.asList(pnt);
	}

	public Iterable<AddressType> getAllAddressTypes() {
		String url = BASE_URL + "addresstypes";
		AddressType[] adt = template.getForObject(url, AddressType[].class);
		return Arrays.asList(adt);
	}

	// Person Phone Numbers
	//
	// ----------------------------------------------------
	//
	public Phone getPhone(Integer id) {
		String url = BASE_URL + "phones/" + id;
		Phone p = template.getForObject(url, Phone.class);
		return p;
	}

	public Iterable<Phone> getAllPhones() {
		String url = BASE_URL + "phones";
		return Arrays.asList(template.getForObject(url, Phone[].class));
	}

	public Iterable<Phone> getAllPhonesByPref(String pref) {
		String url = BASE_URL + "phones/pref=" + pref;
		return Arrays.asList(template.getForObject(url, Phone[].class));
	}

	public Iterable<Phone> getAllPhonesByTypes() {
		String url = BASE_URL + "phones/list";
		return Arrays.asList(template.getForObject(url, Phone[].class));
	}
	
	public Iterable<Phone> getAllPhonesByTypes(String type) {
		String url = BASE_URL + "phones/type=" + type;
		Phone[] pp = template.getForObject(url, Phone[].class);
		return Arrays.asList(pp);
	}
	
	public String createPhone(Phone pp) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Phone> entity = new HttpEntity<Phone>(pp, headers);
		return template.exchange(BASE_URL + "phones", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updatePhone(Integer id, @Validated(BasicInfo.class) Phone pp) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Phone> entity = new HttpEntity<Phone>(pp, headers);

		return template.exchange(BASE_URL + "phones/" + id, HttpMethod.PUT, entity, String.class).getBody();
	}
}
