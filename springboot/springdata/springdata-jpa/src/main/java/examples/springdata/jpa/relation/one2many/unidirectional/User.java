package examples.springdata.jpa.relation.one2many.unidirectional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Many
 */
@Entity(name="TBL_USER")
public class User {
    @Id @GeneratedValue
    private int id;
    private String name;

    @ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY)
    private Group group ;
    

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    


    
}
