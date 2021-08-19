package examples.hibernate.domainmodel.lobs;

import java.sql.Clob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;

/**
 * CREATE TABLE Product
    ( 
        id   INTEGER NOT NULL, 
        name VARCHAR(255), 
        warranty CLOB, 
        PRIMARY KEY (id) 
    );
 * @author farukon
 *
 */
class LobsEntity {
	@Entity(name = "Product")@Getter@Setter
	static class Product {

		@Id
		private Integer id;
		private String name;
		@Lob
		private Clob warranty;


	}
}
