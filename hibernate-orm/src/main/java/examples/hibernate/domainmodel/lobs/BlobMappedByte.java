package examples.hibernate.domainmodel.lobs;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

class BlobMappedByte {
	@Entity(name = "Product")@Getter@Setter
	static class Product {

		@Id
		private Integer id;

		private String name;

		@Lob
		private byte[] image;


	}
}
