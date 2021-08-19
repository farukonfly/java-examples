package examples.hibernate.domainmodel.subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

class SubSelect {
	@Entity(name = "Client")
	@Table(name = "client")
	@Getter@Setter@NoArgsConstructor
	public static class Client {

		@Id
		private Long id;

		@Column(name = "first_name")
		private String firstName;

		@Column(name = "last_name")
		private String lastName;

		// Getters and setters omitted for brevity

	}

	@Entity(name = "Account")
	@Table(name = "account")
	@Getter@Setter@NoArgsConstructor
	public static class Account {

		@Id
		private Long id;

		@ManyToOne
		private Client client;

		private String description;

		// Getters and setters omitted for brevity

	}

	@Entity(name = "AccountTransaction")
	@Table(name = "account_transaction")
	@Getter@Setter@NoArgsConstructor
	public static class AccountTransaction {

		@Id
		@GeneratedValue
		private Long id;

		@ManyToOne
		private Account account;
		private Integer cents;
		private String description;
	}

	@Entity(name = "AccountSummary")
	@Subselect(
			"select " +
					"	a.id as id, " +
					"	concat(concat(c.first_name, ' '), c.last_name) as clientName, " +
					"	sum(atr.cents) as balance " +
					"from account a " +
					"join client c on c.id = a.client_id " +
					"join account_transaction atr on a.id = atr.account_id " +
					"group by a.id, concat(concat(c.first_name, ' '), c.last_name)"
			)
	@Synchronize( {"client", "account", "account_transaction"} )
	@Getter@Setter@NoArgsConstructor@ToString
	public static class AccountSummary {

		@Id
		private Long id;
		private String clientName;
		private int balance;

		// Getters and setters omitted for brevity

	}
}
