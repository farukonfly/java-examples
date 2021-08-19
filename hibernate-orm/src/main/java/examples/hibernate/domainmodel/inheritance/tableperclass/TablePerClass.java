package examples.hibernate.domainmodel.inheritance.tableperclass;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.Setter;

class TablePerClass {
	@Entity(name = "Account")@Getter@Setter
	@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
	static class Account {

		@Id
		private Long id;
		private String owner;
		private BigDecimal balance;
		private BigDecimal interestRate;

		// Getters and setters are omitted for brevity

	}

	@Entity(name = "DebitAccount")@Getter@Setter
	static class DebitAccount extends Account {

		private BigDecimal overdraftFee;

		// Getters and setters are omitted for brevity

	}

	@Entity(name = "CreditAccount")@Getter@Setter
	static class CreditAccount extends Account {

		private BigDecimal creditLimit;

		// Getters and setters are omitted for brevity

	}
}
