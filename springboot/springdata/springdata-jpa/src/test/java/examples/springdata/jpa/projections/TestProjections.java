package examples.springdata.jpa.projections;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import examples.springdata.jpa.extend.entity.Author;
import examples.springdata.jpa.projections.dto.AuthorView;
import examples.springdata.jpa.projections.spel.BookSummary;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestProjections {
	private @Autowired  AuthorRepository authorRepository ;
	private @Autowired  BookRepository bookRepository ;



	@Test@Disabled
	void projections_spel() {
		BookSummary bookSummary  = this.bookRepository.findByTitle("Effective Java");
		System.out.println(bookSummary.getBookNameAndAuthorName());//lead to n+1
	}

	@Test@Disabled
	@Transactional
	void projections_nplus1() {
		AuthorView authorView = this.authorRepository.findViewByFirstName("Joshua");
		authorView.getBooks(); //lead to n+1
	}

	@Test@Disabled
	void projections_fetch() {
		List<Author> list =  this.authorRepository.getAuthorsAndBooks();
		list.forEach(System.out::println);
	}



	@Configuration
	@EnableAutoConfiguration
	static class Config {
	}
}
