package examples.hibernate.domainmodel.identifiers.mpasid;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class MapsIdEntities {
	@Entity(name = "Person")
	@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	public static class Person {

		@Id
		private Long id;
		@NaturalId
		private String registrationNumber;
		public Person(String registrationNumber) {
			this.registrationNumber = registrationNumber;
		}


		/**
		 *     create table "Person" (
		       "id" bigint not null,
		        "registrationNumber" varchar(255),
		        primary key ("id")
		    )
		     alter table "Person" 
       		add constraint UK_23enodonj49jm8uwec4i7y37f unique ("registrationNumber")
		 * 
		 * 
		 */

	}

	/**
	 * 
	    create table "PersonDetails" (
	       "nickName" varchar(255),
	        "person_id" bigint not null,
	        primary key ("person_id")
	    )


       alter table "PersonDetails" 
       add constraint "FKhs2an6nbjwcfi08q42ogfxcde" 
       foreign key ("person_id") 
       references "Person"
	 *
	 */
	@Entity(name = "PersonDetails")
	@Getter@Setter@NoArgsConstructor
	public  static class PersonDetails  {

		public PersonDetails(String nickName, Person person) {
			this.nickName = nickName ;
			this.person = person ;
		}

		public PersonDetails(String nickName) {
			this.nickName = nickName ;
		}

		@Id
		private Long id; //db里面没有id字段，但是从db查询时，hibernate又会把id封装到这个字段

		private String nickName;
		@OneToOne
		@MapsId
		private Person person; // person_id既是主键又是外键


	}
}
