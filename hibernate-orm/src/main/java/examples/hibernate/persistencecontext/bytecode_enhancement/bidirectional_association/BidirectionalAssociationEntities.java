package examples.hibernate.persistencecontext.bytecode_enhancement.bidirectional_association;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;

class BidirectionalAssociationEntities {
	@Entity(name = "Person")@Getter@Setter
	public static class Person {

		@Id
		private Long id;

		private String name;

		@OneToMany(mappedBy = "author")
		private List<Book> books = new ArrayList<>();

		//Getters and setters are omitted for brevity

	}

	@Entity(name = "Book")@Getter@Setter
	public static class Book {

		@Id
		private Long id;

		private String title;

		@NaturalId
		private String isbn;

		@ManyToOne
		private Person author;

		//Getters and setters are omitted for brevity

	}
}
