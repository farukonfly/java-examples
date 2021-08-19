package examples.hibernate.domainmodel.identifiers.composite_idclass_many2one;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

public class CompositeIdClass_Many2One {
	@Entity(name = "SystemUser")
	@IdClass(PK.class)
	@Getter@Setter
	public static class SystemUser {

		@Id
		@ManyToOne(fetch = FetchType.LAZY)
		private Subsystem subsystem;

		@Id
		private String username;

		private String name;

		// Getters and setters are omitted for brevity
	}

	@Entity(name = "Subsystem")@Getter@Setter
	public static class Subsystem {

		@Id
		private String id;

		private String description;

		// Getters and setters are omitted for brevity
	}

	@Getter@Setter
	public static class PK implements Serializable {
		private Subsystem subsystem;
		private String username;

		public PK(Subsystem subsystem, String username) {
			this.subsystem = subsystem;
			this.username = username;
		}

		private PK() {
		}

		// Getters and setters are omitted for brevity
	}
}
/**
 *     create table "Subsystem" (
       "id" varchar(255) not null,
        "description" varchar(255),
        primary key ("id")
    )

        create table "SystemUser" (
       "username" varchar(255) not null,
        "name" varchar(255),
        "subsystem_id" varchar(255) not null,
        primary key ("subsystem_id", "username")
    )

       alter table "SystemUser" 
       add constraint "FK6pi16y4gwxxdcs7y6u7y6gmun" 
       foreign key ("subsystem_id") 
       references "Subsystem"
 * 
 * 
 * 
 * 
 */
