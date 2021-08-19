package examples.springdata.jpa.relation.one2many.unidirectional.collection;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * One
 */
//@Entity(name="TBL_GROUP")
public class Group {
    @Id @GeneratedValue
    private int id;
    private String name;

    @OneToMany
    @JoinColumn(name="group_id")
    private Set<User> users ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    
}
