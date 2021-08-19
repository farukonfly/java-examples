package examples.hibernate.bytecode_enhancement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class BidirectionalEntities {
	@Getter@Setter@NoArgsConstructor
	@Entity(name = "Person")
	static class Person {

		@Id
		private Long id;
		private String name;
		@OneToMany(mappedBy = "author")
		private List<Book> books = new ArrayList<>();

	}

	@Getter@Setter@NoArgsConstructor
	@Entity(name = "Book")
	static class Book {
		@Id
		private Long id;
		private String title;
		@NaturalId
		private String isbn;
		@ManyToOne
		private Person author;

	}
}
