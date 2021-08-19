package examples.hibernate.caching;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class CacheEntities {
	@Getter@Setter@NoArgsConstructor
	@Entity(name = "Phone")
	@Cacheable
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	static class Phone {

		@Id
		@GeneratedValue
		private Long id;
		private String mobile;

		@ManyToOne
		private Person person;
		@Version
		private int version;

	}
	@Getter@Setter@NoArgsConstructor
	@Entity(name="Person")
	@Cacheable
	@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	static class Person{
		@Id
		@GeneratedValue
		private Long id;
		private String name;

		@NaturalId
		@Column(name = "code", unique = true)
		private String code;
	}
}
