package examples.hibernate.fetch.fetch_profile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Department")
@Table(schema = "h11_fetch")
@Getter@Setter@ToString
@AllArgsConstructor@NoArgsConstructor@RequiredArgsConstructor
public  class Department {


	@Id
	private @NonNull Long id;
	@OneToMany(mappedBy = "department")
	private List<Employee> employees = new ArrayList<>();


}