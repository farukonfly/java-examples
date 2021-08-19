package examples.hibernate.domainmodel.inheritance.mappedsuperclass;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ConcreteEntity extends AbstractP {
	@Id
	private Long id ;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
