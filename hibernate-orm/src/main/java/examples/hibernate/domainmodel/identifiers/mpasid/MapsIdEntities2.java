package examples.hibernate.domainmodel.identifiers.mpasid;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

class MapsIdEntities2 {
	// parent entity has simple primary key

	@Entity(name="Employee")
	@Getter@Setter
	public static class Employee {
		private @Id long empId;
		private String name;
		/**
		 *     create table "Employee" (
		       "empId" bigint not null,
		        "name" varchar(255),
		        primary key ("empId")
		    )
		 * 
		 * 
		 */
	} 

	// dependent entity uses EmbeddedId for composite key

	@Embeddable
	@Getter@Setter@EqualsAndHashCode(onlyExplicitlyIncluded = true)
	public static class DependentId implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private @EqualsAndHashCode.Include long empid;   // corresponds to primary key type of Employee
	}

	@Entity(name="Dependent")
	@Getter@Setter
	public static class Dependent {
		private	@EmbeddedId DependentId id;
		@MapsId("empid")  //  maps the empid attribute of embedded id
		private @ManyToOne Employee emp;
		/**
		 *     create table "Dependent" (
		       "name" varchar(255) not null,
		        "emp_empId" bigint not null,
		        primary key ("emp_empId", "name")
		    )

		        alter table "Dependent" 
		       add constraint "FK4qoe5w4nik3jmy9dquxkqkdcx" 
		       foreign key ("emp_empId") 
		       references "Employee"
		 * 
		 * 
		 */
	}
}
