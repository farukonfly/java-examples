package examples.hibernate.fetch.batch_size;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.NaturalId;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

class BatchSizeEntities {
	@Getter@Setter@NoArgsConstructor@RequiredArgsConstructor
	@Entity(name = "Department")
	static class Department {

		@Id
		private @NonNull Long id;

		@OneToMany(mappedBy = "department",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
		@BatchSize(size = 5)
		private @Setter(AccessLevel.NONE) List<Employee> employees = new ArrayList<>();

		public void setEmployees(List<Employee> employees) {
			employees.forEach(employee->employee.setDepartment(this));
			this.employees = employees;
		}



	}

	@Getter@Setter@NoArgsConstructor@RequiredArgsConstructor
	@Entity(name = "Employee")
	static class Employee {

		@Id
		private @NonNull Long id;

		@NaturalId
		private String name;



		public Employee(@NonNull Long id, String name) {
			this.id = id;
			this.name = name;
		}



		@ManyToOne(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST,CascadeType.MERGE})
		private Department department;

		//Getters and setters omitted for brevity
	}
}
