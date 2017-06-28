package pl.coderslab.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private PublisherDao publisherDao;
	
	
	// UWAGA - takie coś możemy zrobić żeby było tak, że jak jest konkretny parametr 
	// to wchodzi w jakąś metodę, a jak nie ma to w inną
//	@RequestMapping(method = RequestMethod.GET, params="rating")
	
	@ModelAttribute("publishers")
	public List<Publisher> getAllPublishers(){
		return publisherDao.getAll();
	}
	@ModelAttribute("authors")
	public List<Author> getAllAuthors(){
		return authorDao.getAllAuthors();
	}
	
	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public String showAddBookForm(Model model){
		model.addAttribute("book", new Book());
		return "bookForm";
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String processAddBookForm(@ModelAttribute Book book){
		bookDao.save(book);
		return "redirect:/books/list";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
	public String showEditBookForm(Model model, @PathVariable Long id){
		model.addAttribute("book", bookDao.find(id));
		return "bookForm";
	}
	@RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
	public String editBookForm(Model model, @ModelAttribute Book book){
		bookDao.update(book);
		return "redirect:/books/list";
	}
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String showDeleteBookForm(Model model, @PathVariable Long id){
		model.addAttribute("book", bookDao.find(id));
		return "bookDelete";
	}
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
	public String deleteBookForm(Model model, @ModelAttribute Book book){
		bookDao.update(book);
		return "redirect:/books/list";
	}
	
//	@RequestMapping(path = "/delete/list", method = RequestMethod.POST)
//	public String redirectToList(){
//		return "redirect:/books/list";
//	}
	
	@RequestMapping(path = "/delete/deleteNow/{id}", method = RequestMethod.GET)
	public String deleteNowBook(Model model, @PathVariable Long id){
		Book book = new Book();
		book = bookDao.find(id);
		bookDao.delete(book);
		return "redirect:/books/list";
	}
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String showAllBooks(Model model){
		model.addAttribute("books", bookDao.getAll());
		return "booksList";
	}
	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody		// dodaliśmy jacksona więc to się wywala
	public Book addBook(){
		Book book = new Book();
		book.setTitle("Thinking in Java");
		List<Author> authors = new ArrayList<>();
		Author author = new Author();
		author.setFirstName("Adam");
		author.setLastName("Tatar");
		authors.add(author);
		author = new Author();
		author.setFirstName("Karol");
		author.setLastName("Czapla");
		authors.add(author);
		book.setAuthors(authors);
		book.setDescription("learn java");
		Publisher publisher = publisherDao.find(1L);
		List<Publisher> publishers = new ArrayList<>();
		publishers.add(publisher);
		book.setPublisher(publisher);
		
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
	
	@RequestMapping(method = RequestMethod.GET)	
	public List<Book> getAllBooks(){
		return bookDao.getAll(); 
		
	}
	
	@RequestMapping(method = RequestMethod.GET, params="rating")	
	public List<Book> getAllBooksWithRating(@RequestParam BigDecimal rating){
		return bookDao.getAllWithRating(rating); 
	}
	
	
	
	
}
