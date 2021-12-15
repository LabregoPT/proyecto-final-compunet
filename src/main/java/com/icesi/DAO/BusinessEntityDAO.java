package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.model.Businessentity;

@Repository
@Transactional
public class BusinessEntityDAO implements Dao<Businessentity>{
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Optional<Businessentity> get(Integer id) {
		return Optional.ofNullable(em.find(Businessentity.class, id));
	}

	@Override
	public Optional<Businessentity> get(long id) {
		return Optional.ofNullable(em.find(Businessentity.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Businessentity> getAll() {
		Query q = em.createNamedQuery("Businessentity.findAll");
        return q.getResultList();
	}

	@Override
	public void save(Businessentity t) {
		em.persist(t);
	}
	
	

	@Override
	public void update(Businessentity t) {
	    em.merge(t);
	}


	public void delete(long id) {
		Businessentity t = em.find(Businessentity.class, id);
	    em.remove(t);
	}

	@Override
	public void delete(Businessentity t) {
		em.remove(t);
	}


}
