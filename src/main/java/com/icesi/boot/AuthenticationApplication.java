package com.icesi.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.icesi.model.Addresstype;
import com.icesi.model.Phonenumbertype;
import com.icesi.repository.AddressTypeRepository;
import com.icesi.repository.PhoneNumberTypeRepository;


@SpringBootApplication
@EnableJpaRepositories("com.icesi.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.icesi.authentication","com.icesi.model"})
@ComponentScan(basePackages = {"com.icesi.authentication","com.icesi.repository","com.icesi.service","com.icesi.controller","com.icesi.DAO","com.icesi.serviceDAO"})
public class AuthenticationApplication {
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
		
	}
	
	@Bean
	public CommandLineRunner add(AddressTypeRepository adtRepository, PhoneNumberTypeRepository pntRepository) {
		return (args) -> {
			Addresstype a1 = new Addresstype();
			Addresstype a2 = new Addresstype();
			Addresstype a3 = new Addresstype();
			a1.setName("Calle Urbana");
			a2.setName("Troncal");
			a3.setName("Carreras y Avenidas");
			
			adtRepository.save(a1);
			adtRepository.save(a2);
			adtRepository.save(a3);
			
			
			Phonenumbertype p1 = new Phonenumbertype();
			Phonenumbertype p2 = new Phonenumbertype();
			Phonenumbertype p3 = new Phonenumbertype();
			p1.setName("Celular");
			p2.setName("Telefono Fijo");
			p3.setName("Fax");
			
			pntRepository.save(p1);
			pntRepository.save(p2);
			pntRepository.save(p3);
			
		};
	}

}
