package examples.hibernate.domainmodel.inheritance.jointable;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.Getter;
import lombok.Setter;

class InheritanceJoinTable {
	@Entity(name = "Account")@Getter@Setter
	@Inheritance(strategy = InheritanceType.JOINED) 
	static class Account {
		@Id
		private Long id;
		private String owner;
		private BigDecimal balance;
		private BigDecimal interestRate;
	}

	@Entity(name = "DebitAccount")@Getter@Setter
	@PrimaryKeyJoinColumn(name = "account_id")
	static class DebitAccount extends Account {
		private BigDecimal overdraftFee;
	}

	@Entity(name = "CreditAccount")@Getter@Setter
	@PrimaryKeyJoinColumn(name = "account_id")
	static class CreditAccount extends Account {
		private BigDecimal creditLimit;
	}
}
/**
 * create table "Account" (
       "id" bigint not null,
        "balance" decimal(19,2),
        "interestRate" decimal(19,2),
        "owner" varchar(255),
        primary key ("id")
    )

    create table "CreditAccount" (
       "creditLimit" decimal(19,2),
        "account_id" bigint not null,
        primary key ("account_id")
    )

    create table "DebitAccount" (
       "overdraftFee" decimal(19,2),
        "account_id" bigint not null,
        primary key ("account_id")
    )

    alter table "CreditAccount" 
       add constraint "FKgxp9spcm61wlf42lmmfk12yeg" 
       foreign key ("account_id") 
       references "Account" 

    alter table "DebitAccount" 
       add constraint "FKrur5iwg6na9361d9rdb5iekux" 
       foreign key ("account_id") 
       references "Account"
 * 
 */
