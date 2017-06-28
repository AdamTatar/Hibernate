package pl.coderslab.controller;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.coderslab.model.Book;
import pl.coderslab.model.Publisher;

@Controller
public class ValidationController {

	@Autowired
	private Validator validator;
	
	@RequestMapping(path="/validate", method=RequestMethod.GET)
	public String validate(Model model){
		
		Book book = new Book();
		book.setTitle("13");
		book.setRating(BigDecimal.valueOf(1000));
		Set<ConstraintViolation<Book>> errors = validator.validate(book);
		errors.forEach(error-> {
			System.out.println(error.getPropertyPath()+": "+error.getMessage());
		});
		
		Publisher p = new Publisher();
		p.setNip("899-262-03-70");
		p.setRegon("9879879878789789789");
		Set<ConstraintViolation<Publisher>> errors2 = validator.validate(p);
		errors2.forEach(error-> {
			System.out.println(error.getPropertyPath()+": "+error.getMessage());
		});
		
		
		
		
		
		model.addAttribute("errors", errors);
		
		return "errors";
	}
}
