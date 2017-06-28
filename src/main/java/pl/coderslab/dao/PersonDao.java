package pl.coderslab.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import pl.coderslab.model.Person;

@Repository	//bardziej specyficzne niż component
@Transactional
public class PersonDao {
 
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Person entity){
		entityManager.persist(entity);
	}

	public void update(Person entity){
		entityManager.merge(entity);
	}
	
	public Person find(Long id){
		return entityManager.find(Person.class, id);
	}

	public void delete(Person entity){
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
	
	public List<Person> getAll(){

		Person person = new Person();
		Query query = entityManager.createQuery("select a from Person a"); // tu jest inna składnia niż w normalnym zapytaniu
		// nazwa jest nie z tabeli ale z klasy
		List<Person> list = query.getResultList();
		return list;
	}
	
	
	public List<Person> getAllWithRating(BigDecimal rating){
		
		Person person = new Person();
		Query query = entityManager.createQuery("select a from Person a where rating > :rating"); // tu jest inna składnia niż w normalnym zapytaniu
		// nazwa jest nie z tabeli ale z klasy
		query.setParameter("rating", rating);
		List<Person> list = query.getResultList();
		return list;
	}
	
}
