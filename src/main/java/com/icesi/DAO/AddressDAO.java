package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.model.Address;

@Repository
@Transactional
public class AddressDAO implements Dao<Address> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Address> get(Integer id) {
		return Optional.ofNullable(em.find(Address.class, id));
	}

	@Override
	public Optional<Address> get(long id) {
		return Optional.ofNullable(em.find(Address.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> getAll() {
		Query query = em.createQuery("SELECT e FROM Address e");
        return query.getResultList();
	}

	@Override
	public void save(Address t) {
		em.persist(t);
	}

	@Override
	public void update(Address t) {
		em.merge(t);
	}
	
	@Override
	public void delete(Address t) {
		em.remove(t);
	}
	
}
