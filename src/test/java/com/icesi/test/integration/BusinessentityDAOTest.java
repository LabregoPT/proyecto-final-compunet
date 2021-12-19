package com.icesi.test.integration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.icesi.DAO.*;
import com.icesi.boot.AuthenticationApplication;
import com.icesi.model.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = AuthenticationApplication.class)
public class BusinessentityDAOTest {
	
	@Autowired
	private BusinessEntityDAO beRepository;
	
	@Test
	public void saveBusinessentity() {
		BusinessEntity be = new BusinessEntity();
		be.setName("Entidad");
		beRepository.save(be);
		List<BusinessEntity> entities = beRepository.getAll();
		assertEquals(2, entities.size());
	}
	
	
	@Test
	public void findByIDBusinessentity() {
		BusinessEntity beSaved = beRepository.get(500).orElse(null);
		assertNull(beSaved);
	}
	
	@Test
	public void getAllBusinessentity() {
		List<BusinessEntity> entities = beRepository.getAll();
		assertEquals(entities.size(), 1);
	}

	
	@Test
	public void updateBusinessentity() {
		BusinessEntity be = beRepository.get(1).get();
		be.setName("daditnE");
		beRepository.update(be);
		
		String moddedName = beRepository.get(1).get().getName();
		
		assertEquals(moddedName, "daditnE");
		}

}
