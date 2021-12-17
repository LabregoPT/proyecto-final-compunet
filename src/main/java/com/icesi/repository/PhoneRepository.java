package com.icesi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.icesi.model.Phone;


public interface PhoneRepository extends JpaRepository<Phone, Integer> {
	
	
}
