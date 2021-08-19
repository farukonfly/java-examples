package examples.hibernate.domainmodel.where;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Where;

import lombok.Getter;
import lombok.Setter;

class WhereEntities {
	enum AccountType {
		DEBIT, CREDIT
	}

	@Entity(name = "Client")@Getter@Setter
	static class Client {

		@Id
		private Long id;
		private String name;

		@Where(clause = "account_type = 'DEBIT'")
		@OneToMany(mappedBy = "client")
		private List<Account> debitAccounts = new ArrayList<>();

		@Where(clause = "\"account_type\" = 'CREDIT'")
		@OneToMany(mappedBy = "client")
		private List<Account> creditAccounts = new ArrayList<>();


	}

	@Entity(name = "Account")@Getter@Setter
	@Where(clause = "\"active\" = true")
	public static class Account {
		@Id
		private Long id;
		@ManyToOne
		private Client client;
		@Column(name = "account_type")
		@Enumerated(EnumType.STRING)
		private AccountType type;
		private Double amount;
		private Double rate;
		private boolean active;


	}
}
