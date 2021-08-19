package examples.hibernate.fetch.fetch_profile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor@RequiredArgsConstructor
@Entity(name = "Project")
@Table(schema = "h11_fetch")
@NamedEntityGraph(name = "project.employees",attributeNodes = @NamedAttributeNode(
		value = "employees",subgraph = "project.employees.department"),
subgraphs = @NamedSubgraph(name = "project.employees.department",attributeNodes = @NamedAttributeNode( "department" ))
		)
public class Project {
	@Id
	private @NonNull Long id;

	@ManyToMany
	@JoinTable(name="h11_fetch.project_employee",
	joinColumns = {@JoinColumn(name ="project_id" ,referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name="employee_id",referencedColumnName = "id")})
	private List<Employee> employees = new ArrayList<>();
}