package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.model.Businessentityaddress;

@Repository
public class BusinessEntityAddressDAO implements Dao<Businessentityaddress> {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Optional<Businessentityaddress> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Businessentityaddress.class, id));
	}

	@Override
	public Optional<Businessentityaddress> get(long id) {
		return Optional.ofNullable(entityManager.find(Businessentityaddress.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Businessentityaddress> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Businessentityaddress e");
        return query.getResultList();
	}

	@Override
	public void save(Businessentityaddress t) {
		entityManager.persist(t);
		
	}

	@Override
	public void update(Businessentityaddress t) {
		entityManager.merge(t);
	}

	@Override
	public void delete(Businessentityaddress t) {
		entityManager.remove(t);
	}

}
