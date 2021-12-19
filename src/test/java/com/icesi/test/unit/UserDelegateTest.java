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
public class UserDelegateTest {

	private final static String BASE_URL = "http://localhost:8080/";

	@Mock
	private RestTemplate template;

	@InjectMocks
	private UserDelegate subject;

	@Before
	public void pre() {
		ResponseEntity<String> response = new ResponseEntity<>("Body", HttpStatus.OK);
		Mockito.when(template.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any())).thenReturn(response);
	}

	//
	// Business Entities
	// ----------------------------------------------------
	//

	@Test
	public void createEntityAddress() {
		BusinessEntityAddress bea = new BusinessEntityAddress();
		bea.setId(0);
		assertEquals("Body", subject.createEntityAddress(bea));
	}

	@Test
	public void getEntity() {
		int id = 0;
		BusinessEntityAddress ret = new BusinessEntityAddress();
		ret.setId(id);
		String url = BASE_URL + "baddresses/" + id;

		Mockito.when(template.getForObject(url, BusinessEntityAddress.class)).thenReturn(ret);

		assertEquals(ret, subject.getEntity(0));
	}

	@Test
	public void getAllAdresses() {
		String url = BASE_URL + "baddresses";

		Mockito.when(template.getForObject(url, BusinessEntityAddress[].class))
				.thenReturn(new BusinessEntityAddress[0]);

		List<BusinessEntityAddress> response = (List<BusinessEntityAddress>) subject.getAllAdresses();
		assertEquals(0, response.size());
	}

	@Test
	public void updateEntity() {
		BusinessEntityAddress be = new BusinessEntityAddress();
		be.setId(0);

		assertEquals("Body", subject.updateEntity(0, be));
	}

	// Phone Number & Address Types
	//
	// ----------------------------------------------------
	//

	@Test
	public void getAllPhoneTypes() {
		String url = BASE_URL + "phonetypes";

		Mockito.when(template.getForObject(url, PhoneNumberType[].class)).thenReturn(new PhoneNumberType[0]);

		List<PhoneNumberType> response = (List<PhoneNumberType>) subject.getAllPhoneTypes();
		assertEquals(0, response.size());
	}

	@Test
	public void getAllAddressTypes() {
		String url = BASE_URL + "addresstypes";

		Mockito.when(template.getForObject(url, AddressType[].class)).thenReturn(new AddressType[0]);

		List<AddressType> response = (List<AddressType>) subject.getAllAddressTypes();
		assertEquals(0, response.size());
	}

	// Person Phone Numbers
	//
	// ----------------------------------------------------
	//
	
	@Test
	public void createPhone() {
		Phone bea = new Phone();
		bea.setId(0);
		assertEquals("Body", subject.createPhone(bea));
	}

	@Test
	public void getPhone() {
		int id = 0;
		Phone ret = new Phone();
		ret.setId(id);
		String url = BASE_URL + "phones/" + id;

		Mockito.when(template.getForObject(url, Phone.class)).thenReturn(ret);

		assertEquals(ret, subject.getPhone(0));
	}

	@Test
	public void getAllPhones() {
		String url = BASE_URL + "phones";

		Mockito.when(template.getForObject(url, Phone[].class))
				.thenReturn(new Phone[0]);

		List<Phone> response = (List<Phone>) subject.getAllPhones();
		assertEquals(0, response.size());
	}

	@Test
	public void updatePhone() {
		Phone be = new Phone();
		be.setId(0);

		assertEquals("Body", subject.updatePhone(0, be));
	}
}
