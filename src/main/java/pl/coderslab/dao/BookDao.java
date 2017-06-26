package pl.coderslab.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

		List<Book> list = new ArrayList<>();
		Book book = new Book();
		for(Long i = 0L; i<1000L; i++){
			book = entityManager.find(Book.class, i);
			if(book != null){
				list.add(book);
			}
		}
		return list;
	}
	
}
