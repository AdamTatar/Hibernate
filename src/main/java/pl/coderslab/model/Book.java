package pl.coderslab.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity // robimy z JPA a nie z hibernate żeby można było przejść łatwiej nacoś
		// ingeo niż hiberante
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String author;

	@Column(precision = 4, scale = 2)
	private BigDecimal rating;

	@ManyToOne(cascade = CascadeType.ALL)
	private Publisher publisher;

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Column(columnDefinition = "TEXT")
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public BigDecimal getRating() {
		return rating;
	}

	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", rating=" + rating + ", publisher="
				+ publisher + ", description=" + description + "]";
	}

	
	
}
