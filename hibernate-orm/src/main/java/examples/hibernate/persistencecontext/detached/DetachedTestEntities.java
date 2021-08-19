package examples.hibernate.persistencecontext.detached;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class DetachedTestEntities {
	@Entity(name="Person")@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	static class Person{
		@Id
		private Long id ;
		private String name ;
	}
}
