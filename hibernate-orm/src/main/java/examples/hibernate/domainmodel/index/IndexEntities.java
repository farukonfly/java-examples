package examples.hibernate.domainmodel.index;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 	create table author (
	    id bigint not null,
	    first_name varchar(255),
	    last_name varchar(255),
	    primary key (id)
	)

	create index idx_author_first_last_name
	    on author (first_name, last_name)
 *
 */
class IndexEntities {
	@Entity
	@Table(
			name = "author",
			indexes =  @Index(
					name = "idx_author_first_last_name",
					columnList = "first_name, last_name",
					unique = false
					)
			)
	static class Author {

		@Id
		@GeneratedValue
		private Long id;

		@Column(name = "first_name")
		private String firstName;

		@Column(name = "last_name")
		private String lastName;

	}


}
