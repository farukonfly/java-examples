package examples.hibernate.domainmodel.identifiers.optimizer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class OptimizerId {
	@Entity(name = "Product")
	@Getter@Setter@NoArgsConstructor
	public static class Product {

		@Id
		@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "product_generator"
				)
		@GenericGenerator(
				name = "product_generator",
				strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
				parameters = {
						@Parameter(name = "sequence_name", value = "product_sequence"),
						@Parameter(name = "initial_value", value = "1"),
						@Parameter(name = "increment_size", value = "3"),
						@Parameter(name = "optimizer", value = "pooled-lo")
				}
				)
		private Long id;

		@Column(name = "p_name")
		private String name;

		@Column(name = "p_number")
		private String number;

		//Getters and setters are omitted for brevity

	}
}
