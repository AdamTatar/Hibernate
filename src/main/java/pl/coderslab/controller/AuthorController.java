package pl.coderslab.controller;

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

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.model.Author;

@Controller
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private BookDao bookDao;
	
	
	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody		// dodaliśmy jacksona więc to się wywala
	public Author addAuthor(){
		
		Author author = new Author();
		author.setFirstName("Bruce");
		author.setLastName("Eckel");
		authorDao.save(author);
		return author;
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Author getAuthor(@PathVariable Long id){
		Author author = authorDao.find(id);
		return author;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Author updateAuthor(@PathVariable Long id){
		Author author = authorDao.find(id);
		author.setLastName(author.getLastName()+" updated");
		authorDao.update(author);
		return author;
		
	}
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deleteAuthor(@PathVariable Long id){
		Author author = authorDao.find(id);
		authorDao.delete(author);
		
	}
	@RequestMapping(method = RequestMethod.GET)
	public String getAllAuthors(Model model){
		List<Author> authors = new ArrayList<>();
		authors = authorDao.getAllAuthors();
		model.addAttribute("authors", authors);
		return "authorAll";
	}
//	@RequestMapping(method = RequestMethod.GET)
//	public List<Author> getAllAuthors(){
//		List<Author> authors = new ArrayList<>();
//		authors = authorDao.getAllAuthors();
//		return authors;
//	}

	
	@RequestMapping(path = "/add" , method = RequestMethod.GET)
	public String addAuthorFromForm(Model model){
		Author author = new Author();
		model.addAttribute("author", author);
		
		return "authorAdd";
	}
	@RequestMapping(path = "/add" , method = RequestMethod.POST)
	public String addAuthorToDatabase(@Valid @ModelAttribute Author author,BindingResult result, Model model){
		if (result.hasErrors()) {
			return "authorAdd";
		}
		model.addAttribute("author", author);
		authorDao.save(author);
		return "authorAddSuccess";
	}
	
}
