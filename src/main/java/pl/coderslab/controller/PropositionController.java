package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.coderslab.dao.AuthorDao;
import pl.coderslab.dao.BookDao;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Book;
import pl.coderslab.validator.BookValidationGroup;
import pl.coderslab.validator.PropositionValidationGroup;

@Controller
@RequestMapping("/proposition")
public class PropositionController {

	@Autowired
	private BookDao bookDao;
	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private PublisherDao publisherDao;


	@RequestMapping(path = "/add", method = RequestMethod.GET)
	public String showAddPropositionBookForm(Model model) {
		model.addAttribute("book", new Book());
		return "propForm";
	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public String processAddBookPropositionForm(@Validated({PropositionValidationGroup.class, BookValidationGroup.class}) Book book, BindingResult result) {

		if (result.hasErrors()) {
			return "propForm";
		}

		bookDao.save(book);
		return "redirect:/books/list";
	}

}
