package com.icesi.DAO;

import java.util.List;
import java.util.Optional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.model.BusinessEntity;
import com.icesi.model.Person;

@Repository
@Transactional
public class PersonDAO implements Dao<Person>{
	
	
	@PersistenceContext
	private EntityManager em;
	   

	@Override
	public Optional<Person> get(Integer id) {
		return Optional.ofNullable(em.find(Person.class, id));
	}

	@Override
	public Optional<Person> get(long id) {
		return Optional.ofNullable(em.find(Person.class, id));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Person> getAll() {
		Query query = em.createQuery("SELECT e FROM Person e");
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Person> getByTittle(String title) {
		Query query = em.createQuery("SELECT e FROM Person e WHERE title = "+ "\'"+title+"\'");
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Person> getByDate(String date1,String date2) {
		Query query = em.createQuery("SELECT e FROM Person e WHERE modifieddate BETWEEN " + "\'"+date1+"\'" 
				+ " AND " + "\'"+date2+"\'" + " AND counter >= 1 ORDER BY lastname");
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Person> getByID(long id) {
		Query query = em.createQuery("SELECT e FROM Person e WHERE personid="+id);
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Person> getByBEId(long id){
		Query query = em.createNativeQuery("SELECT e FROM Person e WHERE businessentity_businessentityid="+id);
		return query.getResultList();
	}

	@Override
	public void save(Person t) {
	    em.persist(t);
	}

	@Override
	public void update(Person t) {
	    em.merge(t);
	}

	@Override
	public void delete(Person t) {
		em.remove(t);
	}
	
	public void delete(long id) {
		BusinessEntity t = em.find(BusinessEntity.class, id);
	    em.remove(t);
	}


}
