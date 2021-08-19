package examples.hibernate.domainmodel.embeddable.many2one;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;

class EmbeddableMany2One {
	@Embeddable@Getter@Setter
	static class Publisher {

		private String name;
		@ManyToOne(fetch = FetchType.LAZY)
		private Country country;
	}

	@Entity(name = "Country")@Getter@Setter
	static class Country {
		@Id
		@GeneratedValue
		private Long id;
		@NaturalId
		private String name;

	}
}
/**
 * create table Country (
	    id bigint not null,
	    name varchar(255),
	    primary key (id)
	)

	alter table Country
	    add constraint UK_p1n05aafu73sbm3ggsxqeditd
	    unique (name)
 * 
 * 
 * 
 * 
 */
