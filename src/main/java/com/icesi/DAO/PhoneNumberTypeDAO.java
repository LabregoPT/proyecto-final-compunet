package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.icesi.model.PhoneNumberType;


@Repository
public class PhoneNumberTypeDAO implements Dao<PhoneNumberType> {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	
	@Override
	public Optional<PhoneNumberType> get(Integer id) {
		return Optional.ofNullable(entityManager.find(PhoneNumberType.class, id));
	}

	@Override
	public Optional<PhoneNumberType> get(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PhoneNumberType> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Phonenumbertype e");
        return query.getResultList();
	}

	@Override
	public void save(PhoneNumberType t) {
		entityManager.persist(t);
	}

	@Override
	public void update(PhoneNumberType t) {
		entityManager.merge(t);
	}

	@Override
	public void delete(PhoneNumberType t) {
		entityManager.remove(t);
	}
	
}
