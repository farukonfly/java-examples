package examples.hibernate.domainmodel.lobs;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

class ClobMappedString {
	@Entity(name = "Product")@Getter@Setter
	static class Product {

		@Id
		private Integer id;
		private String name;
		@Lob
		private String warranty;

		// Getters and setters are omitted for brevity

	}
}
