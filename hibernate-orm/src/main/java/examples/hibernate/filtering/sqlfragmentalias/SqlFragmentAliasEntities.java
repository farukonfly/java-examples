package examples.hibernate.filtering.sqlfragmentalias;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SqlFragmentAlias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

class SqlFragmentAliasEntities {
	@Getter@Setter@NoArgsConstructor@AllArgsConstructor
	@Entity(name = "Account")
	@Table(name = "account")
	@SecondaryTable(name = "account_details")
	@SQLDelete(sql = "UPDATE account_details SET deleted = true WHERE id = ? ")
	@FilterDef(name = "activeAccount", parameters = @ParamDef(name = "active", type = "boolean"))
	@Filter(name = "activeAccount", condition = "{a}.active = :active and {ad}.deleted = false", aliases = {
			@SqlFragmentAlias(alias = "a", table = "account"),
			@SqlFragmentAlias(alias = "ad", table = "account_details"), })
	public static class Account {

		@Id
		private Long id;

		private Double amount;

		private Double rate;

		private boolean active;

		@Column(table = "account_details")
		private boolean deleted;

		// Getters and setters omitted for brevity

	}
}
