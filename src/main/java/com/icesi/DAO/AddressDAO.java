package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.icesi.model.Address;

@Repository
public class AddressDAO implements Dao<Address> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Address> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Address.class, id));
	}

	@Override
	public Optional<Address> get(long id) {
		return Optional.ofNullable(entityManager.find(Address.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Address e");
        return query.getResultList();
	}

	@Override
	public void save(Address t) {
		entityManager.persist(t);
	}

	@Override
	public void update(Address t) {
		entityManager.merge(t);
	}
	
	
	@Override
	public void delete(Address t) {
		entityManager.remove(t);
	}
	
}
