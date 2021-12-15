package com.icesi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.icesi.model.Stateprovince;

public interface StateProvinceRepository extends JpaRepository<Stateprovince, Integer>{
	
	@Query
	List<Stateprovince> findByStateprovinceid(Integer stateprovinceid);
	
	@Query
	List<Stateprovince> findByName(String name);
}
