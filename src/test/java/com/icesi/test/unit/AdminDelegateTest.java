package com.icesi.test.unit;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.icesi.businessDelegate.*;
import com.icesi.model.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminDelegateTest {
	
	private final static String BASE_URL = "http://localhost:8080/";

	@Mock
	private RestTemplate template;
	
	@InjectMocks
	private AdminDelegate subject;
	
	@Before
	public void pre() {
		ResponseEntity<String> response = new ResponseEntity<>("Body", HttpStatus.OK);
		Mockito.when(template.exchange(
				ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any())).thenReturn(response);
	}
	
	//
	// Business Entities
	//----------------------------------------------------
	//
	
	@Test
	public void createEntity() {
		BusinessEntity be = new BusinessEntity();
		be.setName("Entidad");
		be.setBusinessentityid(0);
		assertEquals("Body", subject.createEntity(be));
	}
	
	@Test
	public void getEntity() {
		int id = 0;
		BusinessEntity ret = new BusinessEntity();
		ret.setBusinessentityid(id);
		String url = BASE_URL+"entities/"+id;
		
		Mockito.when(template.getForObject(url, BusinessEntity.class)).thenReturn(ret);
		
		assertEquals(ret, subject.getEntity(0));
	}
	
	@Test
	public void getAllEntities() {
		BusinessEntity be1 = new BusinessEntity();
		BusinessEntity be2 = new BusinessEntity();
		BusinessEntity[] ret = {be1, be2};
		String url = BASE_URL+"entities";
		
		Mockito.when(template.getForObject(url, BusinessEntity[].class)).thenReturn(ret);
		
		List<BusinessEntity> response = (List<BusinessEntity>) subject.getAllEntities();
		assertEquals(ret.length, response.size());
	}
	
	@Test
	public void updateEntity() {
		BusinessEntity be = new BusinessEntity();
		be.setName("Entidad");
		be.setBusinessentityid(0);
		
		assertEquals("Body", subject.updateEntity(0, be));
	}
	
	
	//Persons
	//
	//----------------------------------------------------
	//
	
	@Test
	public void getPerson() {
		int id = 0;
		Person p = new Person();
		String url = BASE_URL+"/persons/"+id;
		
		Mockito.when(template.getForObject(url, Person.class)).thenReturn(p);
		
		assertEquals(p, subject.getPerson(id));
	}
	
	@Test
	public void getPersons() {
		String url = BASE_URL+"/persons";
		Mockito.when(template.getForObject(url, Person[].class)).thenReturn(new Person[0]);

		List<Person> response = (List<Person>) subject.getPersons();
		
		assertEquals(0, response.size());
	}
	
	//Todos los demás getPersons son modificaciones del anterior.
	
	@Test
	public void createPerson() {
		Person p = new Person();
		
		assertEquals("Body", subject.createPerson(p));
	}
	
	@Test
	public void updatePerson() {
		Person p = new Person();
		p.setPersonid(0);
		assertEquals("Body", subject.updatePerson(0, p));
	}
	
	//States & Provinces
	//
	//----------------------------------------------------
	//
	
	@Test
	public void getState() {
		int id = 0;
		StateProvince sp = new StateProvince();
		String url = BASE_URL+"states/"+id;
		
		Mockito.when(template.getForObject(url, StateProvince.class)).thenReturn(sp);
		
		assertEquals(sp, subject.getState(id));
	}
	
	@Test
	public void getAllStates() {
		String url = BASE_URL+"states";
		Mockito.when(template.getForObject(url, StateProvince[].class)).thenReturn(new StateProvince[0]);

		List<StateProvince> response = (List<StateProvince>) subject.getAllStates();
		
		assertEquals(0, response.size());
	}
	
	@Test
	public void createState() {
		StateProvince p = new StateProvince();
		
		assertEquals("Body", subject.createState(p));
	}
	
	@Test
	public void updateState() {
		StateProvince p = new StateProvince();
		p.setStateprovinceid(0);
		assertEquals("Body", subject.updateState(0, p));
	}
	
	
	//Addresses
	//
	//----------------------------------------------------
	//
	
	@Test
	public void getAddress() {
		int id = 0;
		Address sp = new Address();
		String url = BASE_URL+"addresses/"+id;
		
		Mockito.when(template.getForObject(url, Address.class)).thenReturn(sp);
		
		assertEquals(sp, subject.getAddress(id));
	}
	
	@Test
	public void getAllAddresses() {
		String url = BASE_URL+"addresses";
		Mockito.when(template.getForObject(url, Address[].class)).thenReturn(new Address[0]);

		List<Address> response = (List<Address>) subject.getAllAddresses();
		
		assertEquals(0, response.size());
	}
	
	@Test
	public void createAddresses() {
		Address p = new Address();
		
		assertEquals("Body", subject.createAddresses(p));
	}
	
	@Test
	public void updateAddresses() {
		Address p = new Address();
		p.setAddressid(0);
		assertEquals("Body", subject.updateAddresses(0, p));
	}
	
}
