package examples.hibernate.domainmodel.dblevel_check;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;


class DbLevelCheck {
	@Entity(name = "Book")@Getter@Setter
	@Check( constraints = "CASE WHEN isbn IS NOT NULL THEN LENGTH(isbn) = 13 ELSE true END") //会在DB中创建检查约束
	static class Book {

		@Id
		private Long id;

		private String title;

		@NaturalId
		private String isbn;

		private Double price;

		//Getters and setters omitted for brevity

	}
}
