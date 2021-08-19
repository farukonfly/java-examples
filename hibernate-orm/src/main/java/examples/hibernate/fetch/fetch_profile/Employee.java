package examples.hibernate.fetch.fetch_profile;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
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


@Getter@Setter
@AllArgsConstructor@NoArgsConstructor@RequiredArgsConstructor
@ToString
@Entity(name = "Employee")
@Table(schema = "h11_fetch")
//@NamedEntityGraph(name="employee.projects",attributeNodes = @NamedAttributeNode("projects"))
@FetchProfile(
		name = "employee.projects",
		fetchOverrides = {
				@FetchProfile.FetchOverride(
						entity = Employee.class,
						association = "projects",
						mode = FetchMode.JOIN
						)
		}
		)
public class Employee {

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