package generic;

public class Book implements Comparable<Book>{
	private String title;
	private Double price;

	public Book(String title, Double price) {
		this.title = title;
		this.price = price;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int compareTo(Book b) {
		return this.title.compareTo(b.title);
	}
}
