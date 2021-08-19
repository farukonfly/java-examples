package examples.hibernate.domainmodel.associations.secondarytable.entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "FARUKON_ROLE")
public class ERole extends AbstractEntityObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="ROLECODE",unique = true,nullable = false,length = 255)
	private String rolecode;
	@Column(name="ROLENAME",nullable = false,length = 255 )
	private String rolename;

	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinTable(name = "FARUKON_ROLE_REF_RESOURCE",joinColumns = @JoinColumn(name= "ROLEID"),
	inverseJoinColumns = @JoinColumn(name="RESOURCEID") )
	private Set<EResource> resources = new HashSet<>() ;




	public ERole() {
	}


	public void addResources(EResource... resources) {
		this.resources.addAll(Arrays.asList(resources));
	}



	public ERole(String rolecode, String rolename) {
		this.rolecode = rolecode;
		this.rolename = rolename;
	}
	@Override
	public String toString() {
		return "ERole [rolecode=" + this.rolecode + ", rolename=" + this.rolename + "]";
	}

	public String getRolecode() {
		return this.rolecode;
	}

	public void setRolecode(String rolecode) {
		this.rolecode = rolecode;
	}

	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<EResource> getResources() {
		return this.resources;
	}

	public void setResources(Set<EResource> resources) {
		this.resources = resources;
	}



}
