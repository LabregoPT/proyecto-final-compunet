package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.model.StateProvince;

@Repository
@Transactional
public class StateProvinceDAO implements Dao<StateProvince>{
	
	@PersistenceContext 
	private EntityManager em;
	   
	@Override
	public Optional<StateProvince> get(Integer id) {
		return Optional.ofNullable(em.find(StateProvince.class, id));
	}

	@Override
	public Optional<StateProvince> get(long id) {
		return Optional.ofNullable(em.find(StateProvince.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StateProvince> getAll() {
		Query query = em.createQuery("SELECT e FROM StateProvince e");
        return query.getResultList();
	}

	@Override
	public void save(StateProvince t) {
		em.persist(t);
	}

	@Override
	public void update(StateProvince t) {
		em.merge(t);
	}

	@Override
	public void delete(StateProvince t) {
		em.remove(t);
	}
}
