package examples.hibernate.domainmodel.identifiers.composite_idclass;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class CompositeIdClass {
	@Entity(name = "SystemUser")
	@IdClass(PK.class)
	@Getter@Setter
	@NoArgsConstructor
	public static class SystemUser {

		@Id
		private String subsystem;

		@Id
		private String username;

		private String name;

		public PK getId() {
			return new PK(this.subsystem, this.username);
		}

		public void setId(PK id) {
			this.subsystem = id.getSubsystem();
			this.username = id.getUsername();
		}

		/**
		 *     
		    create table "SystemUser" (
		       "subsystem" varchar(255) not null,
		        "username" varchar(255) not null,
		        "name" varchar(255),
		        primary key ("subsystem", "username")
		    )
		 */
	}

	@Getter@Setter
	@NoArgsConstructor@AllArgsConstructor@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class PK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private @EqualsAndHashCode.Include String subsystem;
		private @EqualsAndHashCode.Include String username;


	}
}
