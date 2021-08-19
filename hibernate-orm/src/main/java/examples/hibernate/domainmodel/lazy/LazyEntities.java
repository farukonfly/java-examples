package examples.hibernate.domainmodel.lazy;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import org.hibernate.annotations.LazyGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class LazyEntities {
	@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	@Entity(name="Customer")
	static class Customer{
		private @Id long id ;
		@Basic( fetch = FetchType.LAZY)@LazyGroup("g2")
		private String field1 ;
		@Basic( fetch = FetchType.LAZY )@LazyGroup("g2")
		private String field2 ;

	}
}
