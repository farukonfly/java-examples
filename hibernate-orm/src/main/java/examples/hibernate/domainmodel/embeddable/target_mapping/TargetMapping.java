package examples.hibernate.domainmodel.embeddable.target_mapping;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Target;

import lombok.Getter;
import lombok.Setter;

class TargetMapping {
	public interface Coordinates {
		double x();
		double y();
	}

	@Embeddable
	public static class GPS implements Coordinates {

		private double latitude;
		private double longitude;

		private GPS() {
		}

		public GPS(double latitude, double longitude) {
			this.latitude = latitude;
			this.longitude = longitude;
		}

		@Override
		public double x() {
			return this.latitude;
		}

		@Override
		public double y() {
			return this.longitude;
		}
	}

	@Entity(name = "City")@Getter@Setter
	static class City {

		@Id
		@GeneratedValue
		private Long id;

		private String name;

		@Embedded
		@Target(GPS.class)
		private Coordinates coordinates;


	}
}
