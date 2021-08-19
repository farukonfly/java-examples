package examples.springdata.jpa.jpaspecificationexecutor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FARUKON_RESOURCE")
public class EResource extends AbstractEntityObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "RESOURCE",nullable = false,length = 255)
	private String resource;


	public EResource() {
	}

	public EResource(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return this.resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}



}
