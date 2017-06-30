package pl.coderslab.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByRatingGreaterThanOrderByRating(BigDecimal rating);
	List<Book> findByTitle(String title);
	List<Book> findByTitleContaining(String title);	// szukanie części tytulu
	List<Book> findByPublisher(Publisher publisher);

	@Query("select b from Book b where b.title = ?1")
	List<Book> findByTitleFromQuery(String title);
	
	
	
	@Query("select a from Book a where a.rating between 3 and 5")
	List<Book> findBooksWithRatingBeetween3And5();
/*	- Listę książek dla których rating jest pomiędzy zadanymi parametrami np. między 3 a 5.

	- Listę książek dla zadanego wydawcy.

	- Pierwszą książkę z zadanej kategorii, z sortowaniem po tytule.
*/

}
