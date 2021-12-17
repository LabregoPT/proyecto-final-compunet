package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.model.Stateprovince;

@Repository
@Transactional
public class StateProvinceDAO implements Dao<Stateprovince>{
	
	@PersistenceContext 
	private EntityManager em;
	   
	@Override
	public Optional<Stateprovince> get(Integer id) {
		return Optional.ofNullable(em.find(Stateprovince.class, id));
	}

	@Override
	public Optional<Stateprovince> get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stateprovince> getAll() {
		Query query = em.createQuery("SELECT e FROM Stateprovince e");
        return query.getResultList();
	}

	@Override
	public void save(Stateprovince t) {
		em.persist(t);
	}

	@Override
	public void update(Stateprovince t) {
		em.merge(t);
	}

	@Override
	public void delete(Stateprovince t) {
		em.remove(t);
	}
}
