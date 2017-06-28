package pl.coderslab.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.coderslab.model.PersonDT;

@Controller
@RequestMapping("/personsDetails")
public class PersonDetailController {

	@RequestMapping(method = RequestMethod.GET)
	public String showPersonDetailsForm(Model model) {
		model.addAttribute("personDetails", new PersonDT());
		return "personDetails";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String registerUser(@ModelAttribute PersonDT personDetails, Model model) {
		System.out.println(personDetails);
		model.addAttribute("personDT", personDetails);
		return "successDetails";
	}
	
	
	@ModelAttribute("countryItems")
	public List<String> checkOptions() {
		String a[] = new String[] { "Polska", "Litwa", "Rosja", "Niemcy" };
		return Arrays.asList(a);
	}

	
	@ModelAttribute("programmingSkills")
	public List<String> checkOptionsskliis() {
		String a[] = new String[] { "java", "ruby", "php", "c++" , "maven", "hibernate"};
		return Arrays.asList(a);
	}
}
