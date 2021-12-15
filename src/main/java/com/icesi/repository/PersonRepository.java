package com.icesi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.icesi.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	
	@Query(value = "select p from Person p where p.businessentity_businessentityid = ?1", nativeQuery = true)
	List<Person> findByBusinessEntityID(long businessId);
	
	@Query("select p from Person p where p.title = ?1")
	List<Person> findByTitle(String title);
	
	@Query(value = "select p from Person p where p.modifieddate = ?1", nativeQuery = true)
	List<Person> findByModifiedDate(String date);
	
	//2.a
	@Query(value = "select * from person p left join businessentity b on p.businessentity_businessentityid = b.businessentityid"
			+ "where p.modifieddate < ?1 and p.modifieddate > ?2"
			+ "order by p.lastname", 
			nativeQuery=true)
	List<Person> findByDateRange(String toDate, String fromDate);
	
	@Query(value = "select *, count(*) from person p group by p.businessentity_businessentityid where p.count(*) = 2",
			nativeQuery=true)
	List<Person> findEmployeesInAtLeast2Departments();
}