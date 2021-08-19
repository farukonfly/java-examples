package examples.hibernate.domainmodel.associations.secondarytable.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@SecondaryTables({ @SecondaryTable(name = "name", pkJoinColumns = {
		@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "student_id") }) })
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "student_id")
	private int studentId;
	@Column(table = "name")
	private String name;

	public Student(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
}