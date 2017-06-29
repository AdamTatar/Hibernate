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
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.NotEmpty;

import pl.coderslab.validator.BookValidationGroup;
import pl.coderslab.validator.ModernDate;
import pl.coderslab.validator.PropositionValidationGroup;

@Entity // robimy z JPA a nie z hibernate żeby można było przejść łatwiej nacoś
		// ingeo niż hiberante
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 5, max = 255, groups={BookValidationGroup.class, Default.class})
	private String title;

	@NotEmpty(groups=BookValidationGroup.class)
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Author> authors = new ArrayList<>();

	@Min(value=1, groups=BookValidationGroup.class)
	@Max(value=10, groups=BookValidationGroup.class)
	@Column(precision = 4, scale = 2)
	private BigDecimal rating;

	@NotNull(groups=BookValidationGroup.class)
	@ManyToOne(fetch = FetchType.EAGER)
	private Publisher publisher;

	@ModernDate(groups=BookValidationGroup.class)
	private Integer publishYear;

	@Size(max = 600)
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@AssertFalse(groups=BookValidationGroup.class)
	@AssertTrue(groups=PropositionValidationGroup.class)
	private Boolean proposition;
	
	
	
	

	// gettery i settery

	public Boolean getProposition() {
		return proposition;
	}

	public void setProposition(Boolean proposition) {
		this.proposition = proposition;
	}

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
