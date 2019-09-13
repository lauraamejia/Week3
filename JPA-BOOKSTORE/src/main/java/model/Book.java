package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Idbooks")
	private Long idbooks;
	@Column(name = "title")
	private String title;
	@Column(name = "price")
	private double price;

	public Long getIdbooks() {
		return idbooks;
	}

	public void setIdbooks(Long idbooks) {
		this.idbooks = idbooks;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Book [idbooks=" + idbooks + ", title=" + title + ", price=" + price + "]";
	}
}
