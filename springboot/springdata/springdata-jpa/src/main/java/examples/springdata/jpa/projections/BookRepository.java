package examples.springdata.jpa.projections;

import org.springframework.data.jpa.repository.JpaRepository;

import examples.springdata.jpa.extend.entity.Book;
import examples.springdata.jpa.projections.spel.BookSummary;

public interface BookRepository extends JpaRepository<Book, Long> {
	/*
	List<BookSummaryView> getBookDetails();
	BookSummary findByTitle(String title);
	Object[] getByTitle(String title);
	@Query("SELECT b.id,b.title from Book b")
	List<Object[]> getIdAndTitle();
	 */

	BookSummary findByTitle(String title);
}
