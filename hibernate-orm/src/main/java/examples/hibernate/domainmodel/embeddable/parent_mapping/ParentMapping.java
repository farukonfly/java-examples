package examples.hibernate.domainmodel.embeddable.parent_mapping;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Parent;
import org.hibernate.annotations.Target;

import lombok.Getter;
import lombok.Setter;

class ParentMapping {
	@Entity(name = "City")@Getter@Setter
	static class City {

		@Id
		private Long id;
		private String name;

		@Embedded
		@Target( GPS.class )
		private GPS coordinates;

		public City() {}
		public City(Long id ) {
			this.id = id ;
		}

	}
	@Embeddable@Getter@Setter
	static class GPS {

		private  double latitude;
		private  double longitude;
		@Parent
		private City city;

		public GPS(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}
		private GPS() {}

	}
}
/**
 *     create table "City" (
       "id" bigint not null,
        "latitude" double not null,
        "longitude" double not null,
        "name" varchar(255),
        primary key ("id")
    )
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
