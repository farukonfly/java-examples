package examples.hibernate.domainmodel.inheritance.mappedsuperclass;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractP extends AbstractPP {
	@Column
	private String p ;




}
