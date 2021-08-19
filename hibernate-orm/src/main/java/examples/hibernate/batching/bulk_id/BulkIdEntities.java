package examples.hibernate.batching.bulk_id;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

class BulkIdEntities {
	@Getter@Setter@NoArgsConstructor@RequiredArgsConstructor
	@Entity(name = "Person")
	@Inheritance(strategy = InheritanceType.JOINED)
	static class Person implements Serializable {

		@Id
		private @NonNull Integer id;
		@Id
		private @NonNull String companyName;

		private String name;
		private boolean employed;
	}
	@Getter@Setter@NoArgsConstructor
	@Entity(name = "Doctor")
	static class Doctor extends Person {
	}
	@Getter@Setter@NoArgsConstructor
	@Entity(name = "Engineer")
	static class Engineer extends Person {
		private boolean fellow;
	}
}
