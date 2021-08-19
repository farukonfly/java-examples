package examples.hibernate.domainmodel.mappingnationalized;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Nationalized;

import lombok.Getter;
import lombok.Setter;

class NVarcharMappingString {
	@Entity(name = "Product")@Getter@Setter
	static class Product {

		@Id
		private Integer id;
		private String name;
		@Nationalized
		private String warranty;

		// Getters and setters are omitted for brevity

	}
}
