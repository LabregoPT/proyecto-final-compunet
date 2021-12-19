package com.icesi.test.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Order;
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
public class StateProvinceDAOTest {
	
	@Autowired
	private StateProvinceDAO spRepository;
	
	@Test
	@Order(1)
	public void shouldSaveStateProvince() {
		StateProvince sp = new StateProvince();
		sp.setName("Cundinamarca");
		spRepository.save(sp);
		List<StateProvince> states = spRepository.getAll(); 
		assertEquals(states.size(), 2);
	}
	
	
	@Test
	@Order(2)
	public void shouldFindByIDStateProvince() {
		StateProvince spSaved = spRepository.get((Integer) 500).orElse(null);
		assertNull(spSaved);
	}
	
	@Test
	@Order(3)
	public void shouldGetAllStateProvince() {
		List<StateProvince> states = spRepository.getAll(); 
		assertEquals(states.size(), 1);
	}

	
	@Test
	@Order(4)
	public void shouldUpdateBusinessentity() {
		StateProvince sp = spRepository.get((Integer) 1).get();
		sp.setName("Cauca");
		spRepository.update(sp);
		String name = spRepository.get((Integer) 1).get().getName();
		assertEquals(name, "Cauca");
	}

}
