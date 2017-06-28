package pl.coderslab.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "Author [id=" + id + ", firstName=" + firstName + ", LastName=" + lastName + "]";
	}
	
	public String getFullAuthorName(){
		return this.firstName+" "+this.lastName;
	}

}

/*#### Zadanie 4

1. W projekcie `Spring01hibernate` utwórz encje o nazwie `Author`.
2. Ustal nazwę tabeli bazy danych dla tej encji na `authors`.
3. Encja ma zawierać następujące pola:
- id 
- firstName
- lastName

4. Utwórz klasę `AuthorDao` - służącą do operacji na tej encji.
5. Utwórz kontroler, realizujący operacje CRUD (create, read, update, delete).*/