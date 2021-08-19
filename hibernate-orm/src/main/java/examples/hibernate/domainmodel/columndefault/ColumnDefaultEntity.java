package examples.hibernate.domainmodel.columndefault;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;

class ColumnDefaultEntity {
	@Entity(name = "Person")@Getter@Setter
	@DynamicInsert
	static class Person {

		@Id
		private Long id;
		@ColumnDefault("'N/A'")
		private String name;
		@ColumnDefault("-1")
		private Long clientId;
	}
	/**
	 * 	CREATE TABLE Person (
			id BIGINT NOT NULL,
			clientId BIGINT DEFAULT -1,
			name VARCHAR(255) DEFAULT 'N/A',
			PRIMARY KEY (id)
			) * 
	 * 
	 * 
	 */

}
