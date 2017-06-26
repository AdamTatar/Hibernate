package pl.coderslab.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.model.Author;
import pl.coderslab.model.Author;

@RestController
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorDao authorDao;
	
	
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
	
}
