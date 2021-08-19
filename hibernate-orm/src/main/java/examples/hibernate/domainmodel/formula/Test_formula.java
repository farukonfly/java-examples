package examples.hibernate.domainmodel.formula;

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
import org.junit.jupiter.api.Test;

import examples.hibernate.domainmodel.formula.FormulaEntities.Account;


public class Test_formula{

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;

	@Test@Disabled
	protected void placeholder() {

	}

	@Test@DisplayName("formula.persist")
	protected void persist() {
		em.getTransaction().begin();
		Account account = new Account( );
		account.setId( 1L );
		account.setCredit( 5000d );
		account.setRate( 1.25 / 100 );
		em.persist( account );
		em.getTransaction().commit();
	}

	@Test@DisplayName("formula.query")
	protected void query()  {
		Account account = em.find( Account.class, 1L );
		assertEquals( Double.valueOf( 62.5d ), account.getInterest());
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(FormulaEntities.Account.class);

	}

	protected static void init_data() {




	}

	@BeforeAll
	protected static void setUp() throws Exception {

		Map<String,String> additionalSettings = new HashMap<>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS formula;SET SCHEMA formula");
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_formula.addMeta(meta);
			sessionFactory = meta.buildMetadata().buildSessionFactory();
			init_data();

		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	@BeforeEach
	protected void openSession() {
		em = getJpaInterface(sessionFactory);
		session = getHibernateInterface_opensession(sessionFactory);
	}

	@AfterEach
	protected void closeSession() {
		session.close();
		em.close();
	}

	@AfterAll
	protected static void tearDown() {
		sessionFactory.close();
	}

	protected static EntityManager getJpaInterface(SessionFactory sessionFactory) {
		Session session = getHibernateInterface_opensession(sessionFactory);
		EntityManager em = session.getEntityManagerFactory().createEntityManager();
		return em ;

	}
	protected static Session getHibernateInterface_opensession(SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}

	protected static Session getHibernateInterface_currentsession(SessionFactory sessionFactory) {
		return sessionFactory.getCurrentSession();
	}



}
