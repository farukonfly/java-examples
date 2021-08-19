package examples.hibernate.domainmodel.associations.notfound;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

class NotFoundEntities {
	@Entity(name = "Person")
	@Table(schema = "H02_DM")
	@Getter@Setter
	public static class Person {

		@Id
		private Long id;

		private String name;

		private String cityName;

		@ManyToOne
		@NotFound ( action = NotFoundAction.IGNORE )
		@JoinColumn(
				name = "cityName",
				referencedColumnName = "name",
				insertable = false,
				updatable = false
				)
		private City city;

		//Getters and setters are omitted for brevity

	}

	@Entity(name="City")
	@Table(schema = "H02_DM")
	@Getter@Setter
	public static class City implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue
		private Long id;

		private String name;

		//Getters and setters are omitted for brevity

	}
}
