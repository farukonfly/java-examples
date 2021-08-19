package examples.hibernate.hql_jpql;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyTemporal;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.ParameterMode;
import javax.persistence.QueryHint;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

class Hql_JPQL_Entities {
	@NamedQueries({
		@NamedQuery(
				name = "get_person_by_name",
				query = "select p from Person p where name = :name"
				)
		,
		@NamedQuery(
				name = "get_read_only_person_by_name",
				query = "select p from Person p where name = :name",
				hints = {
						@QueryHint(
								name = "org.hibernate.readOnly",
								value = "true"
								)
				}
				)
	})
	@NamedStoredProcedureQueries(
			@NamedStoredProcedureQuery(
					name = "sp_person_phones",
					procedureName = "sp_person_phones",
					parameters = {
							@StoredProcedureParameter(
									name = "personId",
									type = Long.class,
									mode = ParameterMode.IN
									),
							@StoredProcedureParameter(
									name = "personPhones",
									type = Class.class,
									mode = ParameterMode.REF_CURSOR
									)
					}
					)
			)
	@Entity(name="Person")@Getter@Setter
	static class Person {

		@Id
		@GeneratedValue
		private Long id;

		private String name;
		private String nickName;
		private String address;

		@Temporal(TemporalType.TIMESTAMP )
		private Date createdOn;

		@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
		@OrderColumn(name = "order_id")
		private List<Phone> phones = new ArrayList<>();

		@ElementCollection
		@MapKeyEnumerated(EnumType.STRING)
		private Map<AddressType, String> addresses = new HashMap<>();

		@Version
		private int version;
	}

	static enum AddressType {
		HOME,
		OFFICE
	}

	@Entity(name="Partner")@Getter@Setter
	static class Partner {

		@Id
		@GeneratedValue
		private Long id;
		private String name;
		@Version
		private int version;
	}

	@Entity(name="Phone")@Getter@Setter
	static class Phone {

		@Id
		private Long id;

		@ManyToOne(fetch = FetchType.LAZY)
		private Person person;

		@Column(name = "phone_number")
		private String number;

		@Enumerated(EnumType.STRING)
		@Column(name = "phone_type")
		private PhoneType type;

		@OneToMany(mappedBy = "phone", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<Call> calls = new ArrayList<>(  );

		@OneToMany(mappedBy = "phone")
		@MapKey(name = "timestamp")
		@MapKeyTemporal(TemporalType.TIMESTAMP )
		private Map<Date, Call> callHistory = new HashMap<>();

		@ElementCollection
		private List<Date> repairTimestamps = new ArrayList<>(  );

		//Getters and setters are omitted for brevity

	}

	static enum PhoneType {
		LAND_LINE,
		MOBILE;
	}

	@Entity@Getter@Setter
	@Table(name = "phone_call")
	public class Call {

		@Id
		@GeneratedValue
		private Long id;

		@ManyToOne
		private Phone phone;

		@Column(name = "call_timestamp")
		private Date timestamp;

		private int duration;


	}

	@Entity@Getter@Setter
	@Inheritance(strategy = InheritanceType.JOINED)
	static class Payment {

		@Id
		@GeneratedValue
		private Long id;

		private BigDecimal amount;

		private boolean completed;

		@ManyToOne
		private Person person;


	}

	@Entity@Getter@Setter
	static class CreditCardPayment extends Payment {
	}

	@Entity@Getter@Setter
	static class WireTransferPayment extends Payment {
	}
}
