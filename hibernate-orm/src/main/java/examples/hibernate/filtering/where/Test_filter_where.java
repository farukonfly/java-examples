package examples.hibernate.filtering.where;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.List;
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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import examples.hibernate.filtering.where.WhereEntities.Account;
import examples.hibernate.filtering.where.WhereEntities.AccountType;
import examples.hibernate.filtering.where.WhereEntities.Client;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_filter_where {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;

	@Test@Order(2)
	protected void where_test() {
		List<Account> accounts = em.createQuery(
				"select a from Account a", Account.class)
				.getResultList();
		assertEquals( 2, accounts.size());
	}

	@Test@Order(1) 
	protected void persist() {
		em.getTransaction().begin();
		Client client = new Client();
		client.setId( 1L );
		client.setName( "John Doe" );
		em.persist( client );

		Account account1 = new Account( );
		account1.setId( 1L );
		account1.setType( AccountType.CREDIT );
		account1.setAmount( 5000d );
		account1.setRate( 1.25 / 100 );
		account1.setActive( true );
		account1.setClient( client );
		client.getCreditAccounts().add( account1 );
		em.persist( account1 );

		Account account2 = new Account( );
		account2.setId( 2L );
		account2.setType( AccountType.DEBIT );
		account2.setAmount( 0d );
		account2.setRate( 1.05 / 100 );
		account2.setActive( false );
		account2.setClient( client );
		client.getDebitAccounts().add( account2 );
		em.persist( account2 );

		Account account3 = new Account( );
		account3.setType( AccountType.DEBIT );
		account3.setId( 3L );
		account3.setAmount( 250d );
		account3.setRate( 1.05 / 100 );
		account3.setActive( true );
		account3.setClient( client );
		client.getDebitAccounts().add( account3 );
		em.persist( account3 );
		em.getTransaction().commit();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(WhereEntities.Account.class);
		meta.addAnnotatedClass(WhereEntities.Client.class);


	}

	@BeforeEach
	protected void openSession() {
		em = getJpaInterface(sessionFactory);
		session = getHibernateInterface_opensession(sessionFactory);
	}

	@AfterEach
	protected void closeSession() {
		if (session.isOpen()) {
			session.close();
		}
		if (em.isOpen()) {
			em.close();
		}
	}

	@BeforeAll
	protected static void setUp() throws Exception {
		Map<String, String> additionalSettings = new HashMap<String, String>();
		additionalSettings.put("hibernate.connection.INIT",
				"CREATE SCHEMA IF NOT EXISTS filter;SET SCHEMA filter");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_filter_where.addMeta(meta);
			sessionFactory = meta.buildMetadata().buildSessionFactory();

		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			e.printStackTrace();
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
