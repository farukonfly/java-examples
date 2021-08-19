package examples.springdata.jpa.relation.one2many.bidirectional;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * One
 */
@Entity(name="TBL_GROUP")
public class Group {
	@Id @GeneratedValue
	private int id;
	private String name;

	@OneToMany(mappedBy = "group")
	private Set<User> users ;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}


}
