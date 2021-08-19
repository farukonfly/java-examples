package examples.hibernate.domainmodel.inheritance.mappedsuperclass;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractPP {

	private String pp ;

	public String getPp() {
		return this.pp;
	}

	public void setPp(String pp) {
		this.pp = pp;
	}




}
