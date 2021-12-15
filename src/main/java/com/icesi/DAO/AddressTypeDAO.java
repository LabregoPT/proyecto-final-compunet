package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.icesi.model.Addresstype;

@Repository
public class AddressTypeDAO implements Dao<Addresstype>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Addresstype> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Addresstype.class, id));
	}

	@Override
	public Optional<Addresstype> get(long id) {
		return Optional.ofNullable(entityManager.find(Addresstype.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Addresstype> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Addresstype e");
        return query.getResultList();
	}

	@Override
	public void save(Addresstype t) {
		entityManager.persist(t);
	}

	@Override
	public void update(Addresstype t) {
		entityManager.merge(t);
	}

	@Override
	public void delete(Addresstype t) {
		entityManager.remove(t);
	}
	

}
