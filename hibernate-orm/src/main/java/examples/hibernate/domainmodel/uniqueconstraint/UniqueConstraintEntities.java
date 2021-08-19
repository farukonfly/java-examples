package examples.hibernate.domainmodel.uniqueconstraint;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

class UniqueConstraintEntities {
	@Entity@Getter@Setter
	@Table(
			name = "book",
			uniqueConstraints =  @UniqueConstraint(
					name = "uk_book_title_author",
					columnNames = {
							"title",
							"author_id"
					}
					)
			)
	static class Book {

		@Id
		@GeneratedValue
		private Long id;
		private String title;

		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(
				name = "author_id",
				foreignKey = @ForeignKey(name = "fk_book_author_id")
				)
		private Author author;
		/**
		 * create table author (
		    id bigint not null,
		    first_name varchar(255),
		    last_name varchar(255),
		    primary key (id)
		)

		create table book (
		    id bigint not null,
		    title varchar(255),
		    author_id bigint,
		    primary key (id)
		)

		alter table book
		   add constraint uk_book_title_author
		   unique (title, author_id)

		alter table book
		   add constraint fk_book_author_id
		   foreign key (author_id)
		   references author
		 * 
		 * 
		 * 
		 * 
		 */
	}

	@Entity@Getter@Setter
	@Table(name = "author")
	public static class Author {

		@Id
		@GeneratedValue
		private Long id;

		@Column(name = "first_name")
		private String firstName;

		@Column(name = "last_name")
		private String lastName;

		//Getter and setters omitted for brevity
	}
}
