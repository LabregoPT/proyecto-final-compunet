package com.icesi.integration.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.boot.AuthenticationApplication;
import com.icesi.DAO.*;
import com.icesi.model.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class AddressDAOTest {
	
	@Autowired
	private AddressDAO adRepo;
	
	
	@Autowired
	private StateProvinceDAO spRepo;
	
	@Test
	@Order(1)
	public void saveAddress() {
		Address a = new Address();
		Integer id = 1;
		a.setStateprovince(spRepo.get(id).get());
		a.setAddressline1("Calle");
		a.setCity("Ciudad");
		a.setPostalcode("000000");
		a.setModifieddate(Timestamp.valueOf(LocalDateTime.now()));
		adRepo.save(a);
		List<Address> ads = adRepo.getAll();
		assertEquals(ads.size(), 2);
	}
	
	@Test
	@Order(2)
	public void findByIdAddress() {
		Integer id = 500;
		Address a = adRepo.get(id).orElse(null);
		assertNull(a);
	}
	
	@Test
	@Order(3)
	public void getAllAddress() {
		List<Address> ads = adRepo.getAll();
		assertEquals(ads.size(), 1);
	}
	
	@Test
	@Order(4)
	public void updateAddress() {
		Integer id = 1;
		Address toMod = adRepo.get(id).get();
		toMod.setCity("Ciudad2");
		adRepo.update(toMod);
		
		Address modded = adRepo.get(id).get();
		assertEquals(modded.getCity(), "Ciudad2");
	}
	
	@Test
	@Order(5)
	public void findByDate() {
		List<Address> ads = adRepo.findByDate("2021-12-16");
		assertEquals(ads.size(), 1);
	}
}
