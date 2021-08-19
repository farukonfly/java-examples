package examples.hibernate.fetch.lazy_collection;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

class LazyCollectionEntities {
	@Entity(name = "Department")@Getter@Setter@NoArgsConstructor@RequiredArgsConstructor
	static class Department {

		@Id
		private @NonNull Long id;

		@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
		@OrderColumn(name = "order_id")
		@LazyCollection(LazyCollectionOption.EXTRA)//LazyCollectionOption.EXTRA 访问所有元素可能会导致 N+1 查询问题。
		private List<Employee> employees = new ArrayList<>();

		public void addEmployee(Employee employee) {
			employee.setDepartment(this);
			this.employees.add(employee);

		}


	}

	@Entity(name = "Employee")@Getter@Setter@NoArgsConstructor@RequiredArgsConstructor
	public static class Employee {

		@Id
		private @NonNull  Long id;

		@NaturalId
		private String username;

		@ManyToOne(fetch = FetchType.LAZY)
		private Department department;


	}
}
