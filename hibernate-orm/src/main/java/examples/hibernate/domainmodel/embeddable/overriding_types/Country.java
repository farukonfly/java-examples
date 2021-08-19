package examples.hibernate.domainmodel.embeddable.overriding_types;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;

/**
 * create table Country (
    id bigint not null,
    name varchar(255),
    primary key (id)
)

	alter table Country
    add constraint UK_p1n05aafu73sbm3ggsxqeditd
    unique (name)
 * @author farukon
 *
 */

@Entity(name = "Country")
class Country {

	@Id
	@GeneratedValue
	private Long id;

	@NaturalId
	private String name;

	public Country(String name) {
		this.name = name ;
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

	//Getters and setters are omitted for brevity



}