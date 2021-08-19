package examples.hibernate.domainmodel.associations.one2many;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

class One2ManyEntities {
	@Entity(name = "Library")@Getter@Setter
	public static class Library {

		@Id
		private Long id;

		private String name;

		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name = "library_id")
		private Set<Book> books = new HashSet<>();

		// Getters and setters are omitted for brevity
	}

	@Entity(name = "Book")@Getter@Setter
	public static class Book {

		@Id
		@GeneratedValue
		private Long id;

		private String title;

		private String author;

		// Getters and setters are omitted for brevity

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || this.getClass() != o.getClass()) {
				return false;
			}
			Book book = (Book) o;
			return Objects.equals(this.id, book.id);
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.id);
		}
	}
}
