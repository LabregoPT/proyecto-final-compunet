package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.icesi.model.Personphone;
import com.icesi.model.Phonenumbertype;
@Repository
@Transactional
public class PersonPhoneDAO implements Dao<Personphone> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Personphone> get(Integer id) {
		return Optional.ofNullable(em.find(Personphone.class, id));
	}

	@Override
	public Optional<Personphone> get(long id) {
		return Optional.ofNullable(em.find(Personphone.class, id));
	}
	
	@SuppressWarnings("unchecked")
	public List<Personphone> getByPrefix(String pref){
		Query query = em.createQuery("SELECT e FROM Personphone e WHERE phone LIKE "+"\'"+pref+"%\'");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Phonenumbertype> getTypes(){
		Query query = em.createQuery("SELECT e FROM Phonenumbertype e");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Personphone> getByType(String type){
		Query query = em.createQuery("SELECT e FROM Personphone e WHERE phonenumbertype.name = " +"\'"+type+"\'");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Personphone> getAll() {
		Query query = em.createQuery("SELECT e FROM Personphone e");
        return query.getResultList();
	}

	@Override
	public void save(Personphone t) {
		em.persist(t);
	}

	@Override
	public void update(Personphone t) {
		em.merge(t);
	}

	@Override
	public void delete(Personphone t) {
		em.remove(t);
	}

}
