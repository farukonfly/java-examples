package examples.hibernate.filtering.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import examples.hibernate.filtering.filter.FilterEntities.Account;
import examples.hibernate.filtering.filter.FilterEntities.AccountType;
import examples.hibernate.filtering.filter.FilterEntities.Client;


//Filters apply to entity queries, but not to direct fetching.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_filter {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;

	@Test@Order(5)
	protected void filterOnCollection_test() {
		/*
		 * 
		 * 		@Filter(
				name="activeAccount",
				condition="`active_status`= :active"
				)
			private List<Account> accounts = new ArrayList<>( );
			生效
		 * 
		 */
		em
		.unwrap( Session.class )
		.enableFilter( "activeAccount" )
		.setParameter( "active", true);

		Client client = em.find( Client.class, 1L );
		assertEquals( 2, client.getAccounts().size() ); //触发过滤
	}

	@Test@Order(4)@Disabled
	protected void filterNoWorking_test() {
		//定义在Account类上Filter上不会生效
		em
		.unwrap( Session.class )
		.enableFilter( "activeAccount" )
		.setParameter( "active", true);

		Account account = em.find( Account.class, 2L );
		assertFalse( account.isActive() );
	}

	@Test@Order(3)@Disabled
	protected void filter_test() {
		//定义在Account类上Filter上会生效
		em.unwrap( Session.class ).enableFilter( "activeAccount" ).setParameter( "active", true);
		List<Account> accounts = em.createQuery(
				"select a from Account a", Account.class)
				.getResultList(); 

		assertEquals( 2, accounts.size());
	}

	@Test@Order(2)
	protected void nofilter_test() {
		List<Account> accounts = em.createQuery(
				"select a from Account a", Account.class)
				.getResultList();

		assertEquals( 3, accounts.size());
	}

	@Test@Order(1) 
	protected void persist() {
		em.getTransaction().begin();
		Client client = new Client();
		client.setId( 1L );
		client.setName( "John Doe" );


		Account account_1 = new Account();
		account_1.setId( 1L );	
		account_1.setType( AccountType.CREDIT );
		account_1.setAmount( 5000d );
		account_1.setRate( 1.25 / 100 );
		account_1.setActive( true );


		Account account_2 =new Account();
		account_2.setId( 2L );
		account_2.setType( AccountType.DEBIT );
		account_2.setAmount( 0d );
		account_2.setRate( 1.05 / 100 );
		account_2.setActive( false );

		Account account_3=new Account();
		account_3.setType( AccountType.DEBIT );
		account_3.setId( 3L );
		account_3.setAmount( 250d );
		account_3.setRate( 1.05 / 100 );
		account_3.setActive( true );


		client.addAccount(account_1);
		client.addAccount(account_2);
		client.addAccount(account_3);

		em.persist( client );

		em.getTransaction().commit();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(FilterEntities.Account.class);
		meta.addAnnotatedClass(FilterEntities.Client.class);


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
			Test_filter.addMeta(meta);
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
