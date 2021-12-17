package com.icesi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icesi.model.BusinessEntityAddress;

@Repository
public interface BusinessEntityAddressRepository extends JpaRepository<BusinessEntityAddress, Integer> {

}
