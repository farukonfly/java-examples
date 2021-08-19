package examples.hibernate.domainmodel.associations.many2one;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 
 *     create table Phone (
       id bigint not null,
        "number" varchar(255),
        person_id bigint,
        primary key (id)
     )

     alter table Phone add constraint PERSON_ID_FK foreign key (person_id) references Person

 * 
 * 
 *
 */
@Entity(name = "Phone")
public  class Phone {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "`number`")
	private String number;

	@ManyToOne //一对多关系，在多的那方设置JoinColumn
	@JoinColumn(name = "person_id",
	foreignKey = @ForeignKey(name = "PERSON_ID_FK")
			)
	private Person person;  //1个人对应多个手机，1个手机对应1个人

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}



}