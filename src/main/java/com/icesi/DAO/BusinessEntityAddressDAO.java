package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.model.BusinessEntityAddress;

@Repository
@Transactional
public class BusinessEntityAddressDAO implements Dao<BusinessEntityAddress> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Optional<BusinessEntityAddress> get(Integer id) {
		return Optional.ofNullable(em.find(BusinessEntityAddress.class, id));
	}

	@Override
	public Optional<BusinessEntityAddress> get(long id) {
		return Optional.ofNullable(em.find(BusinessEntityAddress.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessEntityAddress> getAll() {
		Query query = em.createQuery("SELECT e FROM BusinessEntityAddress e");
        return query.getResultList();
	}

	@Override
	public void save(BusinessEntityAddress t) {
		em.persist(t);
		
	}

	@Override
	public void update(BusinessEntityAddress t) {
		em.merge(t);
	}

	@Override
	public void delete(BusinessEntityAddress t) {
		em.remove(t);
	}

}
