package pl.coderslab.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.dao.PublisherDao;
import pl.coderslab.model.Publisher;
import pl.coderslab.model.Publisher;

@RestController
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
	
}
