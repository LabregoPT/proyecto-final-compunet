package com.icesi.businessDelegate;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.icesi.model.Addresstype;
import com.icesi.model.BasicInfo;
import com.icesi.model.Businessentityaddress;
import com.icesi.model.Personphone;
import com.icesi.model.Phonenumbertype;

@Component
public class UserDelegate {

	private static final String BASE_URL = "http://localhost:8080/";

	@Autowired
	private RestTemplate template;

	// Business Entities Addresses
	//
	// ----------------------------------------------------
	//
	public Businessentityaddress getEntity(Integer id) {
		String url = BASE_URL + "baddresses/" + id;
		Businessentityaddress bea = template.getForObject(url, Businessentityaddress.class);
		return bea;
	}

	public Iterable<Businessentityaddress> getAllEntities() {
		String url = BASE_URL + "baddresses";
		Businessentityaddress[] bea = template.getForObject(url, Businessentityaddress[].class);
		return Arrays.asList(bea);
	}

	public String createEntityAddress(Businessentityaddress bea) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Businessentityaddress> entity = new HttpEntity<Businessentityaddress>(bea, headers);

		return template.exchange(BASE_URL + "baddresses", HttpMethod.POST, entity, String.class).getBody();
	}

	public String updateEntity(Integer id, @Validated(BasicInfo.class) Businessentityaddress bea) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Businessentityaddress> entity = new HttpEntity<Businessentityaddress>(bea, headers);

		return template.exchange(BASE_URL + "baddresses/" + id, HttpMethod.PUT, entity, String.class).getBody();
	}

	// Phone Number Types
	//
	// ----------------------------------------------------
	//
	public Iterable<Phonenumbertype> getAllPhoneTypes() {
		String url = BASE_URL + "phonetypes";
		Phonenumbertype[] pnt = template.getForObject(url, Phonenumbertype[].class);
		return Arrays.asList(pnt);
	}

	public Iterable<Addresstype> getAllAddressTypes() {
		String url = BASE_URL + "addresstypes";
		Addresstype[] adt = template.getForObject(url, Addresstype[].class);
		return Arrays.asList(adt);
	}

	// Person Phone Numbers
	//
	// ----------------------------------------------------
	//
	public Personphone getPhone(Integer id) {
		String url = BASE_URL + "phones/" + id;
		Personphone pp = template.getForObject(url, Personphone.class);
		return pp;
	}

	public Iterable<Personphone> getAllPhones() {
		String url = BASE_URL + "phones";
		Personphone[] pp = template.getForObject(url, Personphone[].class);
		return Arrays.asList(pp);
	}

	public Iterable<Personphone> getAllPhonesByPref(String pref) {
		String url = BASE_URL + "phones/pref=" + pref;
		Personphone[] pp = template.getForObject(url, Personphone[].class);
		return Arrays.asList(pp);
	}

	public Iterable<Personphone> getAllPhonesByTypes() {
		String url = BASE_URL + "phones/list";
		Personphone[] pp = template.getForObject(url, Personphone[].class);
		return Arrays.asList(pp);
	}

	public String createPhone(Personphone pp) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Personphone> entity = new HttpEntity<Personphone>(pp, headers);

		return template.exchange(BASE_URL + "phones", HttpMethod.POST, entity, String.class).getBody();
	}

	public Iterable<Personphone> getAllPhonesByTypes(String type) {
		String url = BASE_URL + "phones/type=" + type;
		Personphone[] pp = template.getForObject(url, Personphone[].class);
		return Arrays.asList(pp);
	}

	public String updatePhone(Integer id, @Validated(BasicInfo.class) Personphone pp) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Personphone> entity = new HttpEntity<Personphone>(pp, headers);

		return template.exchange(BASE_URL + "phones/" + id, HttpMethod.PUT, entity, String.class).getBody();
	}
}
