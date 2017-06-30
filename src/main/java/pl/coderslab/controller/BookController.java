package pl.coderslab.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.repository.PublisherRepository;

@Controller
@RequestMapping("/books")
public class BookController {

//	@Autowired
//	private BookDao bookDao;
	
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private PublisherDao publisherDao;

	// UWAGA - takie coś możemy zrobić żeby było tak, że jak jest konkretny
	// parametr
	// to wchodzi w jakąś metodę, a jak nie ma to w inną
	// @RequestMapping(method = RequestMethod.GET, params="rating")

	@ModelAttribute("publishers")
	public List<Publisher> getAllPublishers() {
		return publisherDao.getAll();
	}

	@ModelAttribute("authors")
	public List<Author> getAllAuthors() {
		return authorDao.getAllAuthors();
	}

	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public String showAddBookForm(Model model) {
		model.addAttribute("book", new Book());
		return "bookForm";
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
//	public String processAddBookForm(@Validated({BookValidationGroup.class, Default.class}) Book book, BindingResult result) {
	public String processAddBookForm(@Valid Book book, BindingResult result) {

		if (result.hasErrors()) {
			return "bookForm";
		}

		bookRepository.save(book);
		return "redirect:/books/list";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
	public String showEditBookForm(Model model, @PathVariable Long id) {
		model.addAttribute("book", bookRepository.findOne(id));
		return "bookForm";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
	public String editBookForm(Model model, @ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:/books/list";
	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
	public String showDeleteBookForm(Model model, @PathVariable Long id) {
		model.addAttribute("book", bookRepository.findOne(id));
		return "bookDelete";
	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
	public String deleteBookForm(Model model, @ModelAttribute Book book) {
		bookRepository.save(book);
		return "redirect:/books/list";
	}

	// @RequestMapping(path = "/delete/list", method = RequestMethod.POST)
	// public String redirectToList(){
	// return "redirect:/books/list";
	// }

	@RequestMapping(path = "/delete/deleteNow/{id}", method = RequestMethod.GET)
	public String deleteNowBook(Model model, @PathVariable Long id) {
		Book book = new Book();
		book = bookRepository.findOne(id);
		bookRepository.delete(book);
		return "redirect:/books/list";
	}

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public String showAllBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booksList";
	}

	@RequestMapping(method = RequestMethod.POST)
	// @ResponseBody // dodaliśmy jacksona więc to się wywala
	public Book addBook() {
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

		// Publisher p = new Publisher();
		// book.setPublisher(publisherDao.find(2L));
		// publisherDao.save(p);
		// book.setPublisher(p);

		book.setRating(BigDecimal.valueOf(65, 1));

		bookRepository.save(book);

		return book;

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Book getBook(@PathVariable Long id) {
		Book book = bookRepository.findOne(id);

		return book;

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Book updateBook(@PathVariable Long id) {
		Book book = bookRepository.findOne(id);
		book.setDescription(book.getDescription() + " updated");
		bookRepository.save(book);
		return book;

	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteBook(@PathVariable Long id) {
		Book book = bookRepository.findOne(id);
		bookRepository.delete(book);

	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Book> getAllBooks() {
		return bookRepository.findAll();

	}

	@RequestMapping(path="/rating", method = RequestMethod.GET)
	public String getAllBooksWithRating(@RequestParam BigDecimal rating, Model model) {
//		BigDecimal rating = BigDecimal.valueOf(5.5);
		List<Book> books = bookRepository.findByRatingGreaterThanOrderByRating(rating);
		
		model.addAttribute("books", books);
		return "booksList";
		
	}
	@RequestMapping(path="/", method = RequestMethod.GET)
	public String getBookByTitle(@RequestParam String title, Model model) {
		List<Book> books = bookRepository.findByTitle(title);
		
		model.addAttribute("books", books);
		return "booksList";
		
	}
	@RequestMapping(path="/pub1", method = RequestMethod.GET)
	public String getBookByPublisher(Model model) {
		Publisher p = new Publisher();
		p = publisherRepository.findFirstByNip("7831688882");
		List<Book> books = bookRepository.findByPublisher(p);
		
		model.addAttribute("books", books);
		return "booksList";
		
	}
	
	// znajduje ksiazki z ratingiem 3 - 5
	@RequestMapping(path="/rat3to5", method = RequestMethod.GET)
	public String getBooks3To5(Model model) {
		Publisher p = new Publisher();
		List<Book> books = bookRepository.findBooksWithRatingBeetween3And5();
		
		model.addAttribute("books", books);
		return "booksList";
		
	}

}
