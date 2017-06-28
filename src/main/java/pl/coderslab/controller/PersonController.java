package pl.coderslab.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.coderslab.dao.PersonDao;
import pl.coderslab.model.Person;
import pl.coderslab.model.PersonDT;

@Controller
@RequestMapping("/persons")
public class PersonController {
	
	@Autowired PersonDao personDao;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		model.addAttribute("person", new Person());
		return "registerForm";
	}

	
	

//to działa:	
//	@RequestMapping(value = "/register", method = RequestMethod.POST)
//	public String registerUser(@RequestParam String login, @RequestParam String password, @RequestParam String email, Model model) {
//		Person person = new Person();
//		person.setEmail(email);
//		person.setLogin(login);
//		person.setPassword(password);
//		personDao.save(person);
//		model.addAttribute("person", person);
//		return "success";
//	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@RequestParam String login, @RequestParam String password, @RequestParam String email, Model model) {
		Person person = new Person();
		person.setEmail(email);
		person.setLogin(login);
		person.setPassword(password);
		personDao.save(person);
		model.addAttribute("person", person);
		return "success";
	}
	
	

	@ModelAttribute("languages")
	public List<String> checkOptions() {
		String a[] = new String[] { "java", "php", "ruby", "python" };
		return Arrays.asList(a);
	}
	
	

}
