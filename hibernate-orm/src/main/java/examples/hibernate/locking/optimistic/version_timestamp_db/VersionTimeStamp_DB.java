package examples.hibernate.locking.optimistic.version_timestamp_db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.Source;
import org.hibernate.annotations.SourceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class VersionTimeStamp_DB {
	@Getter@Setter@NoArgsConstructor
	@Entity(name = "Person")
	static class Person {

		@Id
		private Long id;
		private String firstName;
		private String lastName;

		@Version
		@Source(value = SourceType.DB)
		private Date version;
	}
}
