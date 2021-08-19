package examples.hibernate.domainmodel.embeddable.collections;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class EmbeddableCollections {
	@Entity(name = "Book")
	@Getter@Setter@NoArgsConstructor
	public static class Book {

		@Id
		private Long id;
		private String title;

		@ElementCollection
		@CollectionTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"))
		private List<Author> authors = new ArrayList<>();

		/**
		 *     create table "Book" (
		       "id" bigint not null,
		        "title" varchar(255),
		        primary key ("id")
		    )

		        create table "book_author" (
		       "book_id" bigint not null,
		        "firstName" varchar(255),
		        "lastName" varchar(255)
		    )
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
	}

	@Embeddable
	@Access( AccessType.PROPERTY )
	@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	public static class Author {

		private String firstName;
		private String lastName;



	}
}
