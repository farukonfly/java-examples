package examples.hibernate.domainmodel.identifiers.composite_embeddedId;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class CompositeEmbeddedId {
	@Entity(name = "SystemUser")
	@Getter@Setter@NoArgsConstructor
	public static class SystemUser {

		@EmbeddedId
		private PK pk;
		private String name;
		/**
		 *     create table "SystemUser" (
	       "subsystem" varchar(255) not null,
	        "username" varchar(255) not null,
	        "name" varchar(255),
	        primary key ("subsystem", "username")
	    )
		 * 
		 * 
		 */
	}

	@Embeddable
	@Getter@Setter@NoArgsConstructor@AllArgsConstructor@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public  static class PK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private @EqualsAndHashCode.Include String subsystem;
		private @EqualsAndHashCode.Include String username;


	}
}
