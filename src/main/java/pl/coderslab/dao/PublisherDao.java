package pl.coderslab.dao;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.coderslab.model.Publisher;

@Repository	
@Transactional
public class PublisherDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Publisher entity){
		entityManager.persist(entity);
	}

	public void update(Publisher entity){
		entityManager.merge(entity);
	}
	
	public Publisher find(Long id){
		return entityManager.find(Publisher.class, id);
	}

	public void delete(Publisher entity){
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
}
