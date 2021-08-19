package examples.hibernate.domainmodel.identifiers.idgenerator.table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Product")@Getter@Setter
public  class Product {

	@Id
	@GeneratedValue(
			strategy = GenerationType.TABLE,
			generator = "table-generator"
			)
	@TableGenerator(
			name =  "table-generator",
			table = "table_identifier",
			pkColumnName = "table_name",
			valueColumnName = "product_id",
			allocationSize = 5
			)
	private Long id;

	@Column(name = "product_name")
	private String name;

	public Product(String name) {
		this.name = name;
	}

	/**
	 * 
	 * create table hibernate_sequences (
		    sequence_name varchar2(255 char) not null,
		    next_val number(19,0),
		    primary key (sequence_name)
		)


		create table table_identifier (
	    table_name varchar2(255 char) not null,
	    product_id number(19,0),
	    primary key (table_name)
		)
	 * 
	 */


}