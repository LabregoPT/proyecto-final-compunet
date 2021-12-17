package com.icesi.boot;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.icesi.model.*;
import com.icesi.repository.*;


@SpringBootApplication
@EnableJpaRepositories("com.icesi.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.icesi.authentication","com.icesi.model"})
@ComponentScan(basePackages = {"com.icesi.authentication","com.icesi.repository","com.icesi.service","com.icesi.controller","com.icesi.DAO","com.icesi.serviceDAO","com.icesi.REST","com.icesi.businessDelegate"})
public class AuthenticationApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
		
	}
	
	//Generación de datos iniciales
	@Bean
	public CommandLineRunner add(AddressTypeRepository adtRepository, PhoneNumberTypeRepository pntRepository, StateProvinceRepository sp, BusinessEntityRepository ep, AddressRepository adp, PersonRepository pep) {
		return (args) -> {
			AddressType a1 = new AddressType();
			AddressType a2 = new AddressType();
			AddressType a3 = new AddressType();
			a1.setName("Calle");
			a2.setName("Carrera");
			a3.setName("Avenida");
			
			adtRepository.save(a1);
			adtRepository.save(a2);
			adtRepository.save(a3);
			
			
			PhoneNumberType p1 = new PhoneNumberType();
			PhoneNumberType p2 = new PhoneNumberType();
			p1.setName("Movil");
			p2.setName("Fijo");
			
			pntRepository.save(p1);
			pntRepository.save(p2);
			
			StateProvince stp = new StateProvince();
			stp.setName("Valle del Cauca");
			sp.save(stp);
			
			BusinessEntity b1 = new BusinessEntity();
			b1.setName("Entidad1");
			ep.save(b1);
			
			Address ad1 = new Address();
			ad1.setAddressline1("Calle 1");
			ad1.setCity("Ciudad 1");
			ad1.setPostalcode("121212");
			adp.save(ad1);
			
			Person pe1 = new Person();
			pe1.setBusinessentity(b1);
			pe1.setFirstname("Pepito");
			pe1.setLastname("Perez");
			pe1.setTitle("Titulo");
			pe1.setModifieddate(LocalDate.now());
			pep.save(pe1);
		};
	}

	@Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }
	
}
