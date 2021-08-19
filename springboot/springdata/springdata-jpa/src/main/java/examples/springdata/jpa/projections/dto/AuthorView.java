package examples.springdata.jpa.projections.dto;

import java.util.List;

public interface  AuthorView {
	String getFirstName();
	String getLastName();

	List<BookView> getBooks();

	interface BookView{
		String getTitle();
	}
}
