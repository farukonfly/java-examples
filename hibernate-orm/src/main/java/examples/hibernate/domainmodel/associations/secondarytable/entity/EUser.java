package examples.hibernate.domainmodel.associations.secondarytable.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.NamedSubgraph;
import javax.persistence.Table;

@NamedEntityGraphs(@NamedEntityGraph(name = "User-with-roles", attributeNodes = {
		@NamedAttributeNode(value = "roles", subgraph = "Roles-with-Resources") }, subgraphs = {
				@NamedSubgraph(name = "Roles-with-Resources", attributeNodes = { @NamedAttributeNode("resources") }) }))
@Entity
@Table(name = "FARUKON_USER")
public class EUser extends AbstractEntityObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "USERNAME",unique = true,nullable = false,length = 255)
	private String username;
	@Column(name = "PASSWORD",nullable = false,length = 255)
	private String password;

	@Column(name="ACCOUNT_NON_EXPIRED")
	private  Boolean accountNonExpired = true ;

	@Column(name="ACCOUNT_NON_LOCKED")
	private  Boolean accountNonLocked = true ;

	@Column(name="CREDENTIALS_NON_EXPIRED")
	private  Boolean credentialsNonExpired = true;

	@Column(name="ENABLED")
	private Boolean enabled = true;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "FARUKON_ROLE_REF_USER", joinColumns = @JoinColumn(name = "USERID"),
	inverseJoinColumns = @JoinColumn(name = "ROLEID"))
	private Set<ERole> roles;

	public EUser() {
	}

	public EUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<ERole> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<ERole> roles) {
		this.roles = roles;
	}

	public Boolean isEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}




}
