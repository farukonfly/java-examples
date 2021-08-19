package examples.hibernate.interceptors_events;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
@Entity(name="Customer")
class Customer {
	@Id
	private Long id ;
	private String name ;
}
