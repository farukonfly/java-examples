package examples.hibernate.domainmodel.inheritance.singletable;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.DiscriminatorFormula;

import lombok.Getter;
import lombok.Setter;

/**
 *     create table "Account" (
       "DTYPE" varchar(31) not null, //存储子类类型
        "id" bigint not null,
        "balance" decimal(19,2),
        "interestRate" decimal(19,2),
        "owner" varchar(255),
        "creditLimit" decimal(19,2),
        "overdraftFee" decimal(19,2),
        primary key ("id")
    )
 * 
 *
 */
class SingleTableEntities {
	@Entity(name = "Account")@Getter@Setter
	@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
	@DiscriminatorFormula(
			"case when debitKey is not null " +
					"then 'Debit' " +
					"else ( " +
					"   case when creditKey is not null " +
					"   then 'Credit' " +
					"   else 'Unknown' " +
					"   end ) " +
					"end "
			)
	static class Account {

		@Id
		private Long id;
		private String owner;
		private BigDecimal balance;
		private BigDecimal interestRate;

	}

	@Entity(name = "DebitAccount")@Getter@Setter
	@DiscriminatorValue(value = "Debit")
	static class DebitAccount extends Account {

		private BigDecimal overdraftFee;

		// Getters and setters are omitted for brevity

	}

	@Entity(name = "CreditAccount")@Getter@Setter
	@DiscriminatorValue(value = "Credit")
	public static class CreditAccount extends Account {

		private BigDecimal creditLimit;

		// Getters and setters are omitted for brevity

	}
}
