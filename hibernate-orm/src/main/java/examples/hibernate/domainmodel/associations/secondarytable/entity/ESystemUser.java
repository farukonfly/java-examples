package examples.hibernate.domainmodel.associations.secondarytable.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SYSTEM_USER")
@Setter@Getter@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@NoArgsConstructor@AllArgsConstructor
public class ESystemUser extends AbstractEntityObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nickname ;

	//XXX: 使用QBE查询时还存在n+1问题
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private  EUser authorizeUser ;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYSTEM_ROLE_REF_USER", joinColumns = @JoinColumn(name = "USERID"),
	inverseJoinColumns = @JoinColumn(name = "ROLEID"))
	private Set<ESystemRole> roles;

	public ESystemUser(EUser authorizeUser) {
		this.authorizeUser = authorizeUser;
	}


}
