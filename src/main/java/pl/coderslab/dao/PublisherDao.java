package pl.coderslab.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.coderslab.model.Author;
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
	
	public List<Publisher> getAll(){

		Publisher publisher = new Publisher();
		TypedQuery<Publisher> query = entityManager.createQuery("select a from Publisher a", Publisher.class); // tu jest inna składnia niż w normalnym zapytaniu
		// nazwa jest nie z tabeli ale z klasy
		List<Publisher> list = query.getResultList();
		return list;
		
		// tu zrobiliśmy typed query - zwraca listę już na pewno konkretnych obiektów, będą miały typ publisher
	}
}
