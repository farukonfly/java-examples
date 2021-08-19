package examples.hibernate.domainmodel.embeddable.nested;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

class EmbeddableEntitys {
	@Entity(name = "Book")
	@Getter@Setter@ToString
	@NoArgsConstructor@RequiredArgsConstructor
	public static class Book {

		@Id
		private @NonNull Long id;
		private String title;
		private String author;
		private Publisher publisher;
		/**
		 * 
		 * 
		 *  create table "Book" (
		 *  "title" varchar(255),
	        "id" bigint not null,
	        "author" varchar(255),
	        "publish_name" varchar(255),
	        "city" varchar(255),
	        "country" varchar(255),
	         primary key ("id")
    		)
		 * 
		 * 
		 * 
		 */
	}

	@Getter@Setter@ToString
	@Embeddable
	public static class Publisher {

		@Column(name="publish_name")
		private String name;

		private Location location;

		public Publisher(String name, Location location) {
			this.name = name;
			this.location = location;
		}

		private Publisher() {
		}

		// Getters and setters are omitted for brevity
	}

	@Getter@Setter@ToString
	@Embeddable
	public static class Location {

		private String country;

		private String city;

		public Location(String country, String city) {
			this.country = country;
			this.city = city;
		}

		private Location() {
		}

		// Getters and setters are omitted for brevity
	}
}
