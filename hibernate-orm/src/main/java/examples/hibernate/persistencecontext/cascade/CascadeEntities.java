package examples.hibernate.persistencecontext.cascade;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class CascadeEntities {
	@Entity(name="Person")@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	static class Person {

		@Id
		private Long id;

		private String name;

		@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
		private List<Phone> phones = new ArrayList<>();

		// Getters and setters are omitted for brevity

		public void addPhone(Phone phone) {
			this.phones.add(phone);
			phone.setOwner(this);
		}
	}

	@Entity(name="Phone")@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	static class Phone {

		@Id
		private Long id;

		@Column(name = "`number`")
		private String number;

		@ManyToOne(fetch = FetchType.LAZY)
		private Person owner;

		// Getters and setters are omitted for brevity
	}
}
