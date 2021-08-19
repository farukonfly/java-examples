package examples.hibernate.domainmodel.identifiers.idgenerator.primarykeyjoincolumn;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NaturalId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

/**
 * 
 *  create table Person (
       	id bigint not null,
        registrationNumber varchar(255),
        primary key (id)
    	)

     alter table Person addconstraint UK_23enodonj49jm8uwec4i7y37f unique (registrationNumber)
 * 
 *
 */

@Entity(name = "Person")@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public  class Person  {

	@Id
	private Long id;

	@NaturalId
	private String registrationNumber;




}