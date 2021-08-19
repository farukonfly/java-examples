package examples.hibernate.domainmodel.formula;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

import lombok.Getter;
import lombok.Setter;

/**
 *     create table "Account" (
       "id" bigint not null,
        "credit" double,
        "rate" double,
        primary key ("id")
    )
 *
 */
class FormulaEntities {
	@Entity(name = "Account")@Getter@Setter
	static class Account {

		@Id
		private Long id;

		private Double credit;

		private Double rate;

		@Formula(value = "\"credit\"* \"rate\"") //数据库进行计算，而不是jvm
		private Double interest;
		/*
		 *     select
        formulaent0_."id" as id1_0_0_,
        formulaent0_."credit" as credit2_0_0_,
        formulaent0_."rate" as rate3_0_0_,
        formulaent0_.credit * formulaent0_.rate as formula1_0_ 
	    from
	        "Account" formulaent0_ 
	    where
	        formulaent0_."id"=?
		 * 
		 * 
		 * 
		 */


	}
}
