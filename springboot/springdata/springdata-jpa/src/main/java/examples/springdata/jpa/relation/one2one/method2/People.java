package examples.springdata.jpa.relation.one2one.method2;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

/**
 * 
*people 表（id，name，sex，birthday）
*address 表 (id，phone，zipcode，address）
*people_address (people_id，address_id)
 * 
 */
@Entity
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;// id
    @Column(name = "name", nullable = true, length = 20)
    private String name;// 姓名
    @Column(name = "sex", nullable = true, length = 1)
    private String sex;// 性别
    @Column(name = "birthday", nullable = true)
    private LocalDateTime birthday;// 出生日期

    @OneToOne(cascade=CascadeType.ALL) //People是关系的维护端，当删除 people，会级联删除 address
    @JoinTable(name="people_address",joinColumns = @JoinColumn(name="people_id"),
    inverseJoinColumns=@JoinColumn(name="address_id")) //通过关联表保存一对一的关系
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    

}
