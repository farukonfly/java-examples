package examples.hibernate.domainmodel.embeddable.overriding_types;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable@Getter@Setter@NoArgsConstructor
class Publisher {

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	private Country country;

	public Publisher(String name) {
		this.name = name ;
	}



}

