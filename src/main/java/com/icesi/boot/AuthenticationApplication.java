package com.icesi.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.icesi.model.Addresstype;
import com.icesi.model.Phonenumbertype;
import com.icesi.model.Stateprovince;
import com.icesi.repository.AddressTypeRepository;
import com.icesi.repository.PhoneNumberTypeRepository;
import com.icesi.repository.StateProvinceRepository;


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
	public CommandLineRunner add(AddressTypeRepository adtRepository, PhoneNumberTypeRepository pntRepository, StateProvinceRepository sp) {
		return (args) -> {
			Addresstype a1 = new Addresstype();
			Addresstype a2 = new Addresstype();
			Addresstype a3 = new Addresstype();
			a1.setName("Calle");
			a2.setName("Carrera");
			a3.setName("Avenida");
			
			adtRepository.save(a1);
			adtRepository.save(a2);
			adtRepository.save(a3);
			
			
			Phonenumbertype p1 = new Phonenumbertype();
			Phonenumbertype p2 = new Phonenumbertype();
			p1.setName("Telefono Movil");
			p2.setName("Telefono Fijo");
			
			pntRepository.save(p1);
			pntRepository.save(p2);
			
			Stateprovince stp = new Stateprovince();
			stp.setName("Valle del Cauca");
			sp.save(stp);
			
		};
	}

	@Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	 }
	
}
