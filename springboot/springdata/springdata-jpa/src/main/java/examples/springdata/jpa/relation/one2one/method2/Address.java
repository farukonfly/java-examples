package examples.springdata.jpa.relation.one2one.method2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;// id
    @Column(name = "phone", nullable = true, length = 11)
    private String phone;// 手机
    @Column(name = "zipcode", nullable = true, length = 6)
    private String zipcode;// 邮政编码
    @Column(name = "address", nullable = true, length = 100)
    private String address;// 地址


    // 如果不需要根据Address级联查询People，可以注释掉
    /*
     * TODO:待做实验
     * 
     * a) 只有OneToOne,OneToMany,ManyToMany上才有mappedBy属性，ManyToOne不存在该属性； b) mappedBy标签一定是定义在the owned
     * side(被拥有方的)，他指向the owning side(拥有方)； c) mappedBy的含义，应该理解为，拥有方能够自动维护跟被拥有方的关系；
     * 
     */
    /*
     * @OneToOne(mappedBy = "address", cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional
     * = false) private People people;
     */


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    /*
     * public People getPeople() { return people; } public void setPeople(People people) {
     * this.people = people; }
     */



}
