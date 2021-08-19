package examples.springdata.jpa.relation.one2many.unidirectional.collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Many
 */
//@Entity(name="TBL_USER")
public class User {
    @Id @GeneratedValue
    private int id;
    private String name;

    

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



    
}
