package examples.hibernate.domainmodel.identifiers.idgenerator.sequence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class SequenceEntities {
	@Entity(name = "Product")@Setter@Getter@NoArgsConstructor
	public static class Product {

		@Id
		@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "sequence-generator"
				)
		@SequenceGenerator(
				name = "sequence-generator",
				sequenceName = "product_sequence",
				allocationSize = 5
				)
		private Long id;

		@Column(name = "product_name")
		private String name;

		public Product(String name) {
			this.name = name ;
		}

	}
}
