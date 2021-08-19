package examples.hibernate.domainmodel.associations.secondarytable.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicUpdate
@Table(name = "SYSTEM_ROLE")
//@SecondaryTable(name="FARUKON_ROLE",pkJoinColumns = @PrimaryKeyJoinColumn(referencedColumnName="id",name ="authorize_role_id"))
//@SecondaryTable(name="FARUKON_ROLE",pkJoinColumns = @PrimaryKeyJoinColumn(referencedColumnName="id",name ="system_role_id"))
//@SecondaryTable(name="FARUKON_ROLE",foreignKey = @ForeignKey)
@SecondaryTable(name="FARUKON_ROLE",pkJoinColumns = @PrimaryKeyJoinColumn(name ="system_role_id"))
@Setter@Getter
@NoArgsConstructor//@AllArgsConstructor
public class ESystemRole extends AbstractEntityObject    {
	private static final long serialVersionUID = 1L;

	//	@Id
	//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//	private Long id ;
	//XXX: 使用QBE查询时还存在n+1问题
	//	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	//	@Fetch(FetchMode.JOIN)
	//	private ERole authorizeRole ;

	@Column(name="ROLECODE",table="FARUKON_ROLE")
	private String rolecode;
	@Column(name="ROLENAME",table = "FARUKON_ROLE" )
	private String rolename;
	public ESystemRole(String rolecode, String rolename) {
		this.rolecode = rolecode;
		this.rolename = rolename;
	}



}
