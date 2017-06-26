package pl.coderslab.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Book;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private PublisherDao publisherDao;
	
	
	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody		// dodaliśmy jacksona więc to się wywala
	public Book addBook(){
		Book book = new Book();
		book.setTitle("Thinking in Java");
		book.setAuthor("Bruce Eckel");
		book.setDescription("learn java");
		
//		Publisher p = new Publisher();
//		book.setPublisher(publisherDao.find(2L));
//		publisherDao.save(p);
//		book.setPublisher(p);
		
		book.setRating(BigDecimal.valueOf(65,1));
		
		bookDao.save(book);
		
		return book;
		
	}
	
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Book getBook(@PathVariable Long id){
		Book book = bookDao.find(id);
		
		return book;
		
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Book updateBook(@PathVariable Long id){
		Book book = bookDao.find(id);
		book.setDescription(book.getDescription()+" updated");
		bookDao.update(book);
		return book;
		
	}
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable Long id){
		Book book = bookDao.find(id);
		bookDao.delete(book);
		
	}
	
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	public List<Book> getAllBooks(){
		return bookDao.getAll();
		
	}
}
