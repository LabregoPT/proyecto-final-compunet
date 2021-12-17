package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.icesi.model.AddressType;

@Repository
public class AddressTypeDAO implements Dao<AddressType>{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Optional<AddressType> get(Integer id) {
		return Optional.ofNullable(em.find(AddressType.class, id));
	}

	@Override
	public Optional<AddressType> get(long id) {
		return Optional.ofNullable(em.find(AddressType.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AddressType> getAll() {
		Query query = em.createQuery("SELECT e FROM Addresstype e");
        return query.getResultList();
	}

	@Override
	public void save(AddressType t) {
		em.persist(t);
	}

	@Override
	public void update(AddressType t) {
		em.merge(t);
	}

	@Override
	public void delete(AddressType t) {
		em.remove(t);
	}
	

}
