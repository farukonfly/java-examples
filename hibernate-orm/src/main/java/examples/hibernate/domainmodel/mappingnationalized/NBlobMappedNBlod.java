package examples.hibernate.domainmodel.mappingnationalized;

import java.sql.NClob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Nationalized;

import lombok.Getter;
import lombok.Setter;

class NBlobMappedNBlod {
	@Entity(name = "Product")@Getter@Setter
	static class Product {

		@Id
		private Integer id;
		private String name;

		@Lob
		@Nationalized
		// Clob also works, because NClob extends Clob.
		// The database type is still NCLOB either way and handled as such.
		private NClob warranty;


	}
}
/**
 *  String warranty = "My product warranty";
	final Product product = new Product();
	product.setId( 1 );
	product.setName( "Mobile phone" );
	product.setWarranty( NClobProxy.generateProxy( warranty ) );
	entityManager.persist( product );
	--------------------------------------------------------------------------------
	Product product = entityManager.find( Product.class, productId );
	try (Reader reader = product.getWarranty().getCharacterStream()) {
	    assertEquals( "My product warranty", toString( reader ) );
	}
 * 
 */
