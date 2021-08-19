package examples.hibernate.batching.naive_batch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class NaiveBatchEntities {
	@Getter@Setter@NoArgsConstructor
	@Entity(name = "Person")
	static class Person {
		@Id@GeneratedValue
		private Long id ;
		private String name ;
		public Person(String name) {
			this.name = name;
		}


	}
}
