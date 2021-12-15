package com.icesi.DAO;

import java.util.List;
import java.util.Optional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.model.Businessentity;
import com.icesi.model.Person;

@Repository
@Transactional
public class PersonDAO implements Dao<Person>{
	
	
	@PersistenceContext
	private EntityManager entityManager;
	   

	@Override
	public Optional<Person> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Person.class, id));
	}

	@Override
	public Optional<Person> get(long id) {
		return Optional.ofNullable(entityManager.find(Person.class, id));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Person e");
        return query.getResultList();
	}

	@Override
	public void save(Person t) {
	    entityManager.persist(t);
	}

	@Override
	public void update(Person t) {
	    entityManager.merge(t);
	}

	@Override
	public void delete(Person t) {
		entityManager.remove(t);
	}
	
	public void delete(long id) {
		Businessentity t = entityManager.find(Businessentity.class, id);
	    entityManager.remove(t);
	}


}
