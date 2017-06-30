package pl.coderslab.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	List<Publisher> findByRegon(String regon);
	Publisher findFirstByNip(String nip);
	
	

}
