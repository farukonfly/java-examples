package examples.hibernate.persistencecontext.cascade;

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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import examples.hibernate.persistencecontext.cascade.CascadeEntities.Person;
import examples.hibernate.persistencecontext.cascade.CascadeEntities.Phone;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_cascade {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;


	@Test@Order(2)
	protected void cascade_merge() {
		em.getTransaction().begin();
		Phone phone = em.find( Phone.class, 1L );
		Person person = phone.getOwner();

		person.setName( "John Doe Jr." );
		phone.setNumber( "987-654-3210" );

		//em.clear(); 有这条语句会导致级更新失效，即只更新Person，而不会级联更新Phone
		em.merge( person );
		em.getTransaction().commit();

	}

	@Test@Order(1) 
	protected void cascade_persist() {
		em.getTransaction().begin();
		Person person = new Person();
		person.setId( 1L );
		person.setName( "John Doe" );

		Phone phone = new Phone();
		phone.setId( 1L );
		phone.setNumber( "123-456-7890" );

		person.addPhone( phone );

		em.persist( person );

		em.getTransaction().commit();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(CascadeEntities.Person.class);
		meta.addAnnotatedClass(CascadeEntities.Phone.class);


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
				"CREATE SCHEMA IF NOT EXISTS cascode;SET SCHEMA cascode");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_cascade.addMeta(meta);
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
