package examples.hibernate.fetch.graph_fetch;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

class GraphFetchEntities {

	@Getter@Setter@ToString@AllArgsConstructor@NoArgsConstructor@RequiredArgsConstructor
	@Entity(name = "Department")
	static  class Department {
		@Id
		private @NonNull Long id;
		@OneToMany(mappedBy = "department")
		private List<Employee> employees = new ArrayList<>();
	}

	@Getter@Setter@AllArgsConstructor@NoArgsConstructor@RequiredArgsConstructor
	@ToString
	@Entity(name = "Employee")
	@NamedEntityGraph(name="employee.projects",attributeNodes = @NamedAttributeNode("projects"))
	static class Employee {

		@Id
		private @NonNull Long id;
		@NaturalId
		private String username;
		@Column(name = "pswd")
		//@ColumnTransformer(read = "decrypt( 'AES', '00', pswd  )", write = "encrypt('AES', '00', ?)")
		private String password;
		private int accessLevel;
		//@ManyToOne(fetch = FetchType.EAGER)
		@ManyToOne(fetch = FetchType.LAZY)
		@LazyToOne(LazyToOneOption.NO_PROXY)
		private Department department;
		@ManyToMany(mappedBy = "employees",cascade = CascadeType.PERSIST)
		private List<Project> projects = new ArrayList<>();

	}


	@Getter@Setter@NoArgsConstructor@AllArgsConstructor@RequiredArgsConstructor
	@Entity(name = "Project")
	@NamedEntityGraph(name = "project.employees",attributeNodes = @NamedAttributeNode(
			value = "employees",subgraph = "project.employees.department"),
	subgraphs = @NamedSubgraph(name = "project.employees.department",attributeNodes = @NamedAttributeNode( "department" ))
			)
	static class Project {
		@Id
		private @NonNull Long id;

		@ManyToMany
		@JoinTable(name="project_employee",
		joinColumns = {@JoinColumn(name ="project_id" ,referencedColumnName = "id")},
		inverseJoinColumns = {@JoinColumn(name="employee_id",referencedColumnName = "id")})
		private List<Employee> employees = new ArrayList<>();
	}
}
