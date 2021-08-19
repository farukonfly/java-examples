package examples.hibernate.domainmodel.identifiers.idgenerator.primarykeyjoincolumn;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


/**
 * 
 * 
 *     create table PersonDetails (
       id bigint not null,
        nickName varchar(255),
        primary key (id)
    )
 *
 */

@Entity(name = "PersonDetails")
public  class PersonDetails  {

	@Id
	private Long id;

	private String nickName;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Person person; //主键关联 (PersonDetails的id应时Person的id)


	public PersonDetails() {

	}

	public PersonDetails(Long id, String nickName) {
		this.id = id;
		this.nickName = nickName;
	}

	public PersonDetails(Long id, String nickName, Person person) {
		this.id = id;
		this.nickName = nickName;
		this.person = person;
	}

	/**
	 * Access field {@link examples.hibernate.h02_domainmodel.identifiers.idgenerator.mpasid.PersonDetails#person}
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
		this.id = person.getId();
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Person getPerson() {
		return this.person;
	}


}