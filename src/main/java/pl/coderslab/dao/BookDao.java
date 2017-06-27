package pl.coderslab.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.coderslab.model.Book;

@Repository	//bardziej specyficzne niż component
@Transactional
public class BookDao {
 
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Book entity){
		entityManager.persist(entity);
	}

	public void update(Book entity){
		entityManager.merge(entity);
	}
	
	public Book find(Long id){
		return entityManager.find(Book.class, id);
	}

	public void delete(Book entity){
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
	
	public List<Book> getAll(){

		Book book = new Book();
		Query query = entityManager.createQuery("select a from Book a"); // tu jest inna składnia niż w normalnym zapytaniu
		// nazwa jest nie z tabeli ale z klasy
		List<Book> list = query.getResultList();
		return list;
	}
	
	
	public List<Book> getAllWithRating(BigDecimal rating){
		
		Book book = new Book();
		Query query = entityManager.createQuery("select a from Book a where rating > :rating"); // tu jest inna składnia niż w normalnym zapytaniu
		// nazwa jest nie z tabeli ale z klasy
		query.setParameter("rating", rating);
		List<Book> list = query.getResultList();
		return list;
	}
	
}
