package examples.hibernate.domainmodel.lobs;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

/**
 * byte[] image = new byte[] {1, 2, 3};

	final Product product = new Product();
	product.setId( 1 );
	product.setName( "Mobile phone" );

	product.setImage( BlobProxy.generateProxy( image ) );

	entityManager.persist( product )
 * @author farukon
 *
 */
class BlobMappedBlob {
	@Entity(name = "Product")@Getter@Setter
	public static class Product {

		@Id
		private Integer id;

		private String name;

		@Lob
		private Blob image;



	}
}
