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
@Transactional
public class BusinessEntityAddressDAO implements Dao<Businessentityaddress> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Optional<Businessentityaddress> get(Integer id) {
		return Optional.ofNullable(em.find(Businessentityaddress.class, id));
	}

	@Override
	public Optional<Businessentityaddress> get(long id) {
		return Optional.ofNullable(em.find(Businessentityaddress.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Businessentityaddress> getAll() {
		Query query = em.createQuery("SELECT e FROM Businessentityaddress e");
        return query.getResultList();
	}

	@Override
	public void save(Businessentityaddress t) {
		em.persist(t);
		
	}

	@Override
	public void update(Businessentityaddress t) {
		em.merge(t);
	}

	@Override
	public void delete(Businessentityaddress t) {
		em.remove(t);
	}

}
