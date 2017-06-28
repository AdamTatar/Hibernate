package pl.coderslab.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import pl.coderslab.validator.ModernDate;

@Entity // robimy z JPA a nie z hibernate żeby można było przejść łatwiej nacoś
		// ingeo niż hiberante
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 5, max = 255)
	private String title;

	@NotEmpty
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Author> authors = new ArrayList<>();

	@Min(1)
	@Max(10)
	@Column(precision = 4, scale = 2)
	private BigDecimal rating;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Publisher publisher;

	@ModernDate
	private Integer publishYear;

	@Size(max = 600)
	@Column(columnDefinition = "TEXT")
	private String description;

	// gettery i settery

	public Integer getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(Integer publishYear) {
		this.publishYear = publishYear;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
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
		return "Book [id=" + id + ", title=" + title + ", authors=" + authors + ", rating=" + rating + ", publisher="
				+ publisher + ", description=" + description + "]";
	}

}
