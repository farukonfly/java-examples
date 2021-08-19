package examples.hibernate.filtering.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.ParamDef;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


class FilterEntities {
	enum AccountType {
		DEBIT, CREDIT
	}
	@Getter@Setter@NoArgsConstructor@AllArgsConstructor@Builder
	@Entity(name = "Account")
	@FilterDef(name="activeAccount",parameters = @ParamDef(name="active",type="boolean"))
	@Filter(
			name="activeAccount",
			condition="`active_status` = :active"
			)
	static class Account {

		@Id
		private Long id;

		@ManyToOne(fetch = FetchType.LAZY)
		@LazyToOne(LazyToOneOption.NO_PROXY)
		private Client client;  //Account维护关系

		@Column(name = "account_type")
		@Enumerated(EnumType.STRING)
		private AccountType type;

		private Double amount;

		private Double rate;

		@Column(name = "active_status")
		private boolean active;

		//Getters and setters omitted for brevity
	}

	@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	@Entity(name = "Client")
	static class Client {

		@Id
		private Long id;
		private String name;

		@OneToMany(
				mappedBy = "client",
				cascade = CascadeType.ALL
				)
		@Filter(
				name="activeAccount",
				condition="`active_status`= :active"
				)
		private List<Account> accounts = new ArrayList<>( );

		//Getters and setters omitted for brevity

		public void addAccount(Account account) {
			/*一定需要有这一句，因为Account和client之间的关系由Account维护，
			如果没有这一句，Account还是会级联保存，但是Account和client之前
			的关系不会被保存
			 */
			account.setClient( this ); 
			this.accounts.add( account );
		}
	}
}
