package examples.hibernate.domainmodel.subselect;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import examples.hibernate.domainmodel.subselect.SubSelect.Account;
import examples.hibernate.domainmodel.subselect.SubSelect.AccountSummary;
import examples.hibernate.domainmodel.subselect.SubSelect.AccountTransaction;
import examples.hibernate.domainmodel.subselect.SubSelect.Client;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_SubSelect {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;


	@Test@Disabled
	protected void refrush_SubSelectEntity() {
		AccountSummary summary = em.find( AccountSummary.class, 1L );
		assertEquals( "John Doe", summary.getClientName() );
		assertEquals( 100 * 7000, summary.getBalance() );

		em.getTransaction().begin();
		AccountTransaction transaction = new AccountTransaction();
		transaction.setAccount( em.getReference( Account.class, 1L ) );
		transaction.setDescription( "Shopping" );
		transaction.setCents( -100 * 2200 );
		em.persist( transaction );
		em.flush();

		//em.refresh( summary );
		em.detach(summary);
		summary = em.find( AccountSummary.class, 1L );
		assertEquals( 100 * 4800, summary.getBalance() );
	}

	@Test@DisplayName("SubSelect.query")@Order(100)
	protected void subselect() {
		AccountSummary summary = em.createQuery(
				"select s " +
						"from AccountSummary s " +
						"where s.id = :id", AccountSummary.class)
				.setParameter( "id", 1L )
				.getSingleResult();
		System.out.println(summary);


	}
	@Test@DisplayName("SubSelect.persist")@Order(1)
	protected void persist() {
		em.getTransaction().begin();
		Client client = new Client();
		client.setId(1L);
		client.setFirstName("John");
		client.setLastName("Doe");
		em.persist(client);

		Account account = new Account();
		account.setId(1L);
		account.setClient(client);
		account.setDescription("Checking account");
		em.persist(account);

		AccountTransaction transaction = new AccountTransaction();
		transaction.setAccount(account);
		transaction.setDescription("Salary");
		transaction.setCents(100 * 7000);
		em.persist(transaction);
		em.getTransaction().commit();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(SubSelect.Account.class);
		meta.addAnnotatedClass(SubSelect.Client.class);
		meta.addAnnotatedClass(SubSelect.AccountTransaction.class);
		meta.addAnnotatedClass(SubSelect.AccountSummary.class);

	}


	@BeforeEach
	protected void openSession() {
		em = getJpaInterface(sessionFactory);
		session = getHibernateInterface_opensession(sessionFactory);
	}

	@AfterEach
	protected void closeSession() {
		if(session.isOpen()) {
			session.close();
		}
		if(em.isOpen()) {
			em.close();
		}
	}

	@BeforeAll
	protected static void setUp() throws Exception {
		Map<String,String> additionalSettings = new HashMap<>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS SUBSELECTS;SET SCHEMA SUBSELECTS");
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		try {
			meta = new MetadataSources(registry);
			Test_SubSelect.addMeta(meta);
			sessionFactory = meta.buildMetadata().buildSessionFactory();

		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	@AfterAll
	protected static void tearDown() {
		session.close();
		em.close();
		sessionFactory.close();
	}

	protected static EntityManager getJpaInterface(SessionFactory sessionFactory) {
		Session session = getHibernateInterface_opensession(sessionFactory);
		EntityManager em = session.getEntityManagerFactory().createEntityManager();
		return em;

	}

	protected static Session getHibernateInterface_currentsession(SessionFactory sessionFactory) {
		return sessionFactory.getCurrentSession();
	}

	protected static Session getHibernateInterface_opensession(SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}

}
