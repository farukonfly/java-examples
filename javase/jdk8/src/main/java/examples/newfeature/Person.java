package examples.newfeature;

import java.time.LocalDate;

class Person {
	private String name;
	private LocalDate birthday ;



	public Person() {}

	public Person(String name) {
		this.name = name ;
	}

	public Person(String name, LocalDate birthday) {
		this.name = name;
		this.birthday = birthday;
	}







	public LocalDate getBirthday() {
		return this.birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}