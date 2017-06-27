package pl.coderslab.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.coderslab.model.Author;

@Repository	
@Transactional
public class AuthorDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Author entity){
		entityManager.persist(entity);
	}

	public void update(Author entity){
		entityManager.merge(entity);
	}
	
	public Author find(Long id){
		return entityManager.find(Author.class, id);
	}

	public void delete(Author entity){
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
	
	public List<Author> getAllAuthors(){
		List<Author> authors = new ArrayList<>();
		Query query = entityManager.createQuery("select a from Author a");
		authors = query.getResultList();
		return authors;
	}
}
