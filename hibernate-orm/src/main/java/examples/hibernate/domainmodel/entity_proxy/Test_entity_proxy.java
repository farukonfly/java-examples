package examples.hibernate.domainmodel.entity_proxy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_entity_proxy {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;

	@Test@DisplayName("EntityProxy_returnProxy")
	protected void test_entityProxy() {
		session.getTransaction().begin();
		Identifiable book = session.getReference( Book.class, 1L);
		//Identifiable book = session.load(Book.class,1L);
		//Identifiable book = em.getReference(Book.class, 1L);
		assertTrue(book instanceof Identifiable, "Loaded entity is not an instance of the proxy interface");
		assertFalse(book instanceof Book,"Proxy class was not created");

	}
	@Test@DisplayName("EntityProxy_persist")@Order(1)
	protected void persist() {
		em.getTransaction().begin();
		Book book = new Book();
		book.setId( 1L );
		book.setTitle( "High-Performance Java Persistence" );
		book.setAuthor( "Vlad Mihalcea" );
		em.persist(book);
		em.getTransaction().commit();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(Book.class);
	}

	@BeforeEach
	protected void openSession() {
		em = this.getJpaInterface(sessionFactory);
		session = this.getHibernateInterface_opensession(sessionFactory);
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
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS Proxy;SET SCHEMA Proxy");
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_entity_proxy.addMeta(meta);
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
		sessionFactory.close();
	}

	protected EntityManager getJpaInterface(SessionFactory sessionFactory) {
		Session session = this.getHibernateInterface_opensession(sessionFactory);
		EntityManager em = session.getEntityManagerFactory().createEntityManager();
		return em ;

	}

	protected Session getHibernateInterface_currentsession(SessionFactory sessionFactory) {
		return sessionFactory.getCurrentSession();
	}

	protected Session getHibernateInterface_opensession(SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}

}
