package examples.hibernate.domainmodel.identifiers.idgenerator.randomUUID;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Getter;
import lombok.Setter;

class RandomUUIDEntities {
	@Entity(name = "Book")@Getter@Setter
	public static class Book {

		@Id
		@GeneratedValue( generator = "custom-uuid" )
		@GenericGenerator(
				name = "custom-uuid",
				strategy = "org.hibernate.id.UUIDGenerator",
				parameters = {
						@Parameter(
								name = "uuid_gen_strategy_class",
								value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
								)
				}
				)
		private UUID id;

		private String title;

		private String author;

		//Getters and setters are omitted for brevity
	}
}
