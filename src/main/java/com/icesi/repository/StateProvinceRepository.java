package com.icesi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.icesi.model.StateProvince;

public interface StateProvinceRepository extends JpaRepository<StateProvince, Integer>{
	
	@Query
	List<StateProvince> findByStateprovinceid(Integer stateprovinceid);
	
	@Query
	List<StateProvince> findByName(String name);
}
