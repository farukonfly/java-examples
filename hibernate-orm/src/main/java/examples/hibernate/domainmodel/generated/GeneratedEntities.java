package examples.hibernate.domainmodel.generated;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.Getter;
import lombok.Setter;


class GeneratedEntities {

	@Entity(name = "Person")@Getter@Setter
	static class Person {

		@Id
		private Long id;
		private String firstName;
		private String lastName;
		private String middleName1;
		private String middleName2;
		private String middleName3;
		private String middleName4;
		private String middleName5;

		@Generated( value = GenerationTime.ALWAYS )
		@Column(columnDefinition =
		"AS CONCAT(" +
				"	COALESCE(firstName, ''), " +
				"	COALESCE(' ' + middleName1, ''), " +
				"	COALESCE(' ' + middleName2, ''), " +
				"	COALESCE(' ' + middleName3, ''), " +
				"	COALESCE(' ' + middleName4, ''), " +
				"	COALESCE(' ' + middleName5, ''), " +
				"	COALESCE(' ' + lastName, '') " +
				")")
		private String fullName;

	}
}
