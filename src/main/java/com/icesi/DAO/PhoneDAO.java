package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.icesi.model.Phone;
import com.icesi.model.PhoneNumberType;

@Repository
@Transactional
public class PhoneDAO implements Dao<Phone> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<Phone> get(Integer id) {
		return Optional.ofNullable(em.find(Phone.class, id));
	}

	@Override
	public Optional<Phone> get(long id) {
		return Optional.ofNullable(em.find(Phone.class, id));
	}
	
	@SuppressWarnings("unchecked")
	public List<Phone> getByPrefix(String pref){
		Query query = em.createQuery("SELECT e FROM Phone e WHERE phone LIKE "+"\'"+pref+"%\'");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PhoneNumberType> getTypes(){
		Query query = em.createQuery("SELECT e FROM Phonenumbertype e");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Phone> getByType(String type){
		Query query = em.createQuery("SELECT e FROM Phone e WHERE type.name = " +"\'"+type+"\'");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Phone> getAll() {
		Query query = em.createQuery("SELECT e FROM Phone e");
        return query.getResultList();
	}

	@Override
	public void save(Phone t) {
		em.persist(t);
	}

	@Override
	public void update(Phone t) {
		em.merge(t);
	}

	@Override
	public void delete(Phone t) {
		em.remove(t);
	}

}
