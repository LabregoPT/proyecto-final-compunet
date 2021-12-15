package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


import com.icesi.model.Personphone;
@Repository
public class PersonPhoneDAO implements Dao<Personphone> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Personphone> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Personphone.class, id));
	}

	@Override
	public Optional<Personphone> get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Personphone> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Personphone e");
        return query.getResultList();
	}

	@Override
	public void save(Personphone t) {
		entityManager.persist(t);
	}

	@Override
	public void update(Personphone t) {
		entityManager.merge(t);
	}

	@Override
	public void delete(Personphone t) {
		entityManager.remove(t);
	}

}
