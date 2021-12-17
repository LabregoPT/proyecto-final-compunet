package com.icesi.businessDelegate;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import com.icesi.model.*;

@Component
public class AdminDelegate {
	
	@Autowired
	private RestTemplate template;
	
	private static final String BASE_URL = "http://localhost:8080/";
	
	//Business Entities
	//
	//----------------------------------------------------
	//
	
	public Businessentity getEntity(long id) {
		String url = BASE_URL+"entities/"+id;
		Businessentity entity = template.getForObject(url, Businessentity.class);
		return entity;
	}
	
	public Iterable<Businessentity> getAllEntities(){
		String url = BASE_URL+"entities";
		Businessentity[] entities = template.getForObject(url, Businessentity[].class);
		return Arrays.asList(entities);
	}
	
	public String createEntity(Businessentity be) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Businessentity> entity = new HttpEntity<Businessentity>(be,headers);
	    return template.exchange(
	    		BASE_URL+"entities", HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String updateEntity(long id, @Validated(BasicInfo.class) Businessentity be) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Businessentity> entity = new HttpEntity<Businessentity>(be,headers);
	      return template.exchange(
	    		  BASE_URL+"entities/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	//Persons
	//
	//----------------------------------------------------
	//
	
	public Person getPerson(long id){
		String url = BASE_URL+"/persons/"+id;
		Person pe = template.getForObject(url, Person.class);
		return pe;
	}
	
	
	public Iterable<Person> getPersons(){
		String url = BASE_URL+"/persons";
		Person[] pe = template.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}
	
	public Iterable<Person> getAllPersonsByID(long id){
		String url = BASE_URL+"persons/filter="+id;
		Person[] pe = template.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}
	
	public Iterable<Person> getAllPersonsByTitle(String title){
		String url = BASE_URL+"persons/title="+title;
		Person[] pe = template.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}
	
	public Iterable<Person> getAllPersonsByInterval(String date1,String date2){
		String url = BASE_URL+"persons/date1="+date1+"/date2="+date2;
		Person[] pe = template.getForObject(url, Person[].class);
		return Arrays.asList(pe);
	}
	
	public String createPerson(Person pe) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Person> entity = new HttpEntity<Person>(pe,headers);
	    return template.exchange(
	    		BASE_URL+"persons", HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String updatePerson(long id, @Validated(BasicInfo.class) Person pe) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Person> entity = new HttpEntity<Person>(pe,headers);      
	      return template.exchange(
	    		  BASE_URL+"persons/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	//States & Provinces
	//
	//----------------------------------------------------
	//
	
	public Stateprovince getState(Integer id){
		String url = BASE_URL+"states/"+id;
		Stateprovince sp = template.getForObject(url, Stateprovince.class);
		return sp;
	}
	
	public Iterable<Stateprovince> getAllStates(){
		String url = BASE_URL+"states";
		Stateprovince[] sp = template.getForObject(url, Stateprovince[].class);
		return Arrays.asList(sp);
	}
	
	public String createState(Stateprovince sp) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(sp,headers);
	    return template.exchange(
	    		BASE_URL+"states", HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String updateState(Integer id, @Validated(BasicInfo.class) Stateprovince sp) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Stateprovince> entity = new HttpEntity<Stateprovince>(sp,headers);
	      return template.exchange(
	    		  BASE_URL+"states/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}
	
	//Addresses
	//
	//----------------------------------------------------
	//
		
	public Address getAddress(Integer id){
		String url = BASE_URL+"addresses/"+id;
		Address ad = template.getForObject(url, Address.class);
		return ad;
	}
	
	
	public Iterable<Address> getAllAddresses(){
		String url = BASE_URL+"addresses";
		Address[] ad = template.getForObject(url, Address[].class);
		return Arrays.asList(ad);
	}
	
	public Iterable<Address> getAllAddressesByID(Integer id){
		String url = BASE_URL+"addresses/filter="+id;
		Address[] ad = template.getForObject(url, Address[].class);
		return Arrays.asList(ad);
	}
	
	public Iterable<Address> getAllAddressesByDate(String date){
		String url = BASE_URL+"addresses/date="+date;
		Address[] ad = template.getForObject(url, Address[].class);
		return Arrays.asList(ad);
	}
	
	public String createAddresses(Address ad) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<Address> entity = new HttpEntity<Address>(ad,headers);
	    return template.exchange(
	         BASE_URL+"addresses", HttpMethod.POST, entity, String.class).getBody();
	}
	
	public String updateAddresses(Integer id, @Validated(BasicInfo.class) Address ad) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Address> entity = new HttpEntity<Address>(ad,headers);
	      return template.exchange(
	         BASE_URL+"addresses/"+id, HttpMethod.PUT, entity, String.class).getBody();
	}
}
