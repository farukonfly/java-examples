package examples.hibernate.domainmodel.identifiers.idgenerator.rowid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.RowId;

@Entity(name = "Product")
@RowId("ROWID")
public  class Product {

	@Id
	private Long id;

	@Column(name = "`name`")
	private String name;

	@Column(name = "`number`")
	private String number;

	public Product() {

	}

	public Product(Long id,String name,String number) {
		this.id = id ;
		this.name = name ;
		this.number = number;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}



}