package com.icesi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.icesi.model.Businessentity;

public interface BusinessEntityRepository extends JpaRepository<Businessentity, Long> {
	
	@Query
	List<Businessentity> findByBusinessentityid(long businessentityId);

	@Query(value="select * from businessentity", nativeQuery=true)//NOTA no hay fecha guardada entonces retorna todas las entradas
	List<Businessentity> findByModifiedDate(String date);
}
