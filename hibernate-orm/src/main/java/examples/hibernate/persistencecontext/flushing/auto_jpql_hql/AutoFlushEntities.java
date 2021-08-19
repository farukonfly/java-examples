package examples.hibernate.persistencecontext.flushing.auto_jpql_hql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class AutoFlushEntities {
	@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	@Entity(name = "Person")
	static class Person {

		@Id
		@GeneratedValue
		private Long id;
		private String name;
		public Person(String name) {
			this.name = name;
		}



		// Getters and setters are omitted for brevity

	}
	@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	@Entity(name = "Advertisement")
	static class Advertisement {

		@Id
		@GeneratedValue
		private Long id;
		private String title;

		// Getters and setters are omitted for brevity

	}
}
