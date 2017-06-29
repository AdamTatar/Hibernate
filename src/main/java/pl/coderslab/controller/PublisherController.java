package pl.coderslab.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Publisher;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

	@Autowired
	private PublisherDao publisherDao;
	
	
	@RequestMapping(method = RequestMethod.POST)
//	@ResponseBody		// dodaliśmy jacksona więc to się wywala
	public Publisher addPublisher(){
		
		Publisher publisher = new Publisher();
		publisher.setName("Helion");
		publisherDao.save(publisher);
		return publisher;
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public Publisher getPublisher(@PathVariable Long id){
		Publisher publisher = publisherDao.find(id);
		return publisher;
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public Publisher updatePublisher(@PathVariable Long id){
		Publisher publisher = publisherDao.find(id);
		publisher.setName(publisher.getName()+" updated");
		publisherDao.update(publisher);
		return publisher;
		
	}
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void deletePublisher(@PathVariable Long id){
		Publisher publisher = publisherDao.find(id);
		publisherDao.delete(publisher);
		
	}
	
	
	
	
	@RequestMapping(path = "/add" , method = RequestMethod.GET)
	public String addPublisherFromForm(Model model){
		Publisher publisher = new Publisher();
		model.addAttribute("publisher", publisher);
		return "publisherAdd";
	}
	@RequestMapping(path = "/add" , method = RequestMethod.POST)
	public String addPublisherToDatabase(@Valid @ModelAttribute Publisher publisher, BindingResult result, Model model){
		if (result.hasErrors()) {
			return "publisherAdd";
		}
		model.addAttribute("publisher", publisher);
		publisherDao.save(publisher);
		return "publisherAddSuccess";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
