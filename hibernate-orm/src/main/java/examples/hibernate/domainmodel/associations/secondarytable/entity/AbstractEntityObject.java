package examples.hibernate.domainmodel.associations.secondarytable.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;


@MappedSuperclass
public abstract class AbstractEntityObject implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "`ID`", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "`CREATED_BY`", nullable = true, length = 255)
	private String createdBy;

	@Basic(fetch = FetchType.LAZY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "`CREATED_DATE`", nullable = true)
	@OrderBy
	private Date createdDate;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "`LAST_MODIFIED_BY`", nullable = true, length = 255)
	private String lastModifiedBy;

	@Basic(fetch = FetchType.LAZY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "`LAST_MODIFIED_DATE`", nullable = true)
	private Date lastModifiedDate;

	@Version
	@Column(name = "`VERSION`")
	private Integer version;

	// TODO:考虑之后放在AuditListener中处理
	@PrePersist
	private void prePersist() {
		this.createdDate = new Date();
	}

	// TODO:考虑之后放在AuditListener中处理
	@PreUpdate
	private void preUpdate() {
		this.lastModifiedDate = new Date();
	}

	@Transient
	public boolean isNew() {
		return null == this.getId();
	}



	@Override
	public String toString() {
		return "AbstractEntityObject [id=" + this.id + ", createdBy=" + this.createdBy + ", createdDate=" + this.createdDate
				+ ", lastModifiedBy=" + this.lastModifiedBy + ", lastModifiedDate=" + this.lastModifiedDate + ", version="
				+ this.version + "]";
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return this.lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}




}
