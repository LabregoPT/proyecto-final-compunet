package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.icesi.model.Stateprovince;

@Repository
public class StateProvinceDAO implements Dao<Stateprovince>{
	
	@PersistenceContext 
	private EntityManager entityManager;
	   
	@Override
	public Optional<Stateprovince> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Stateprovince.class, id));
	}

	@Override
	public Optional<Stateprovince> get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Stateprovince> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Stateprovince e");
        return query.getResultList();
	}

	@Override
	public void save(Stateprovince t) {
		entityManager.persist(t);
	}

	@Override
	public void update(Stateprovince t) {
		entityManager.merge(t);
	}

	@Override
	public void delete(Stateprovince t) {
		entityManager.remove(t);
	}
}
