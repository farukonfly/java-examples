package examples.hibernate.persistencecontext.detached;

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

import examples.hibernate.persistencecontext.detached.DetachedTestEntities.Person;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_working_detached {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;


	@Test@Order(2)
	protected void merging_detached_entity_jpa() {
		Person person = em.find(Person.class, 1L);
		em.clear();
		em.getTransaction().begin();
		person.setName( "Mr. John Doe" );
		person = em.merge( person ); //Person是一个Detached的对象
		em.getTransaction().commit();
	}

	@Test@Order(1) 
	protected void persist() {

		em.getTransaction().begin();
		Person p1 = new Person();
		p1.setId(1L);p1.setName("john doe");
		em.persist(p1);
		em.getTransaction().commit();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(DetachedTestEntities.Person.class);


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
				"CREATE SCHEMA IF NOT EXISTS flushing;SET SCHEMA flushing");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_working_detached.addMeta(meta);
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
