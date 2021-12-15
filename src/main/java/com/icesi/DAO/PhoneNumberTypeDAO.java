package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.icesi.model.Phonenumbertype;


@Repository
public class PhoneNumberTypeDAO implements Dao<Phonenumbertype> {
	
	@PersistenceContext
	private EntityManager entityManager;

	
	
	@Override
	public Optional<Phonenumbertype> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Phonenumbertype.class, id));
	}

	@Override
	public Optional<Phonenumbertype> get(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Phonenumbertype> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Phonenumbertype e");
        return query.getResultList();
	}

	@Override
	public void save(Phonenumbertype t) {
		entityManager.persist(t);
	}

	@Override
	public void update(Phonenumbertype t) {
		entityManager.merge(t);
	}

	@Override
	public void delete(Phonenumbertype t) {
		entityManager.remove(t);
	}
	
}
