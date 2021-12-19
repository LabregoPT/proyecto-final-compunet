package com.icesi.test.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.DAO.*;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class BusinessAddressTypeDAOTest {
	
	
	@Autowired
	private BusinessEntityAddressDAO beaRepository;
	
	@Autowired
	private BusinessEntityDAO beRepository;
	
	@Autowired
	private AddressDAO adRepository;
	
	@Autowired
	private AddressTypeDAO adtRepository;
	
	public void pre() {
		BusinessEntityAddress bea = new BusinessEntityAddress();
		Address ad = new Address();
		Integer id = 1;
		
		ad.setAddressline1("Calle");
		ad.setCity("Ciudad");
		ad.setPostalcode("00000");
		adRepository.save(ad);
		bea.setAddress(ad);
		BusinessEntity be = new BusinessEntity();
		be.setName("Entidad");
		beRepository.save(be);
		bea.setBusinessentity(be);
		bea.setAddresstype(adtRepository.get(id).get());
		beaRepository.save(bea);
	}
	
	@Test
	public void saveBusinessentity() {
		BusinessEntityAddress bea = new BusinessEntityAddress();
		Address ad = new Address();
		Integer id = 1;
		
		ad.setAddressline1("Calle1");
		ad.setCity("Ciudad1");
		ad.setPostalcode("00001");
		adRepository.save(ad);
		bea.setAddress(ad);
		BusinessEntity be = new BusinessEntity();
		be.setName("Entidad1");
		beRepository.save(be);
		bea.setBusinessentity(be);
		bea.setAddresstype(adtRepository.get(id).get());
		beaRepository.save(bea);
		List<BusinessEntityAddress> entities = beaRepository.getAll(); 
		
		assertEquals(1, entities.size());
	}
	
	
	@Test
	public void findByBEID() {
		Integer id = 500;
		BusinessEntityAddress beaSaved = beaRepository.get(id).orElse(null);
		
		assertNull(beaSaved);
	}
	
	@Test
	public void getAllBEAddress() {
		pre();
		List<BusinessEntityAddress> entities = beaRepository.getAll(); 
		
		assertEquals(1, entities.size());
	}
	
	@Test
	public void updateBEAddress() {
		pre();
		Integer id = 1;
		BusinessEntityAddress bea = beaRepository.get(id).get();
		
		Address ad1 = new Address();
		ad1.setAddressline1("Calle2");
		ad1.setCity("Bogota");
		ad1.setPostalcode("00003");
		adRepository.save(ad1);
		
		bea.setAddress(ad1);
		beaRepository.update(bea);
		
		Address updated = beaRepository.get(id).get().getAddress();
		assertEquals(updated.getCity(), "Bogota");}
	
}
