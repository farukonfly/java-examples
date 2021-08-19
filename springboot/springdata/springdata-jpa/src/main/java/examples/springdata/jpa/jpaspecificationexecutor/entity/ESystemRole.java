package examples.springdata.jpa.jpaspecificationexecutor.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicUpdate
@Table(name = "SYSTEM_ROLE")
@Setter@Getter@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@NoArgsConstructor@AllArgsConstructor
public class ESystemRole extends AbstractEntityObject {
	private static final long serialVersionUID = 1L;

	//XXX: 使用QBE查询时还存在n+1问题
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	private ERole authorizeRole ;



}
