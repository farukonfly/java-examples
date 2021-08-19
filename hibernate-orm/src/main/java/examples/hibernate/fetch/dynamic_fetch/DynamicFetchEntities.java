package examples.hibernate.fetch.dynamic_fetch;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class DynamicFetchEntities {
	@Getter@Setter@AllArgsConstructor@NoArgsConstructor
	@Entity(name = "Department")
	static class Department {
		@Id
		private Long id;
	}

	@Getter@Setter@AllArgsConstructor@NoArgsConstructor
	@Entity(name = "Employee")
	static class Employee {

		@Id
		private Long id;
		@NaturalId
		private String username;
		@ManyToOne(fetch = FetchType.EAGER) //会通过left outer join连接
		private Department department;
	}
	/**
	 create table Employee (
       id bigint not null,
        username varchar(255),
        department_id bigint,
        primary key (id)
    	)
	 * */
}
