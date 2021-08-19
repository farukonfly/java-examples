package examples.hibernate.domainmodel.embeddable.basic;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

class EmbeddableBasic {
	/**
	 * CREATE CACHED TABLE "CONTACT"
	 * ( "ID" INTEGER NOT NULL,
	 *  "FIRST" VARCHAR(255), * #{@link Contact.Name#first}
	 *  "LAST" VARCHAR(255), #{@link Contact.Name#last}
	 *  "MIDDLE" VARCHAR(255), #{@link Contact.Name#middle} 
	 *  "NOTES" VARCHAR(255),
	 *  "STARRED" BOOLEAN NOT NULL )
	 * 
	 * @author farukon
	 *
	 */
	@Entity(name = "Contact")@Getter@Setter
	public static class Contact {
		@Id
		private Integer id;
		// 嵌入映射
		private Name name;
		private String notes;
		private boolean starred;

		@Embeddable@Getter@Setter
		public static class Name {
			private String first;
			private String middle;
			private String last;
		}



	}
}
