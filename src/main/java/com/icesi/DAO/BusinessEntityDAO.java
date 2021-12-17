package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.model.BusinessEntity;

@Repository
@Transactional
public class BusinessEntityDAO implements Dao<BusinessEntity>{
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Optional<BusinessEntity> get(Integer id) {
		return Optional.ofNullable(em.find(BusinessEntity.class, id));
	}

	@Override
	public Optional<BusinessEntity> get(long id) {
		return Optional.ofNullable(em.find(BusinessEntity.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BusinessEntity> getAll() {
		Query q = em.createQuery("SELECT b from BusinessEntity b");
        return q.getResultList();
	}

	@Override
	public void save(BusinessEntity t) {
		em.persist(t);
	}
	
	

	@Override
	public void update(BusinessEntity t) {
	    em.merge(t);
	}


	public void delete(long id) {
		BusinessEntity t = em.find(BusinessEntity.class, id);
	    em.remove(t);
	}

	@Override
	public void delete(BusinessEntity t) {
		em.remove(t);
	}


}
