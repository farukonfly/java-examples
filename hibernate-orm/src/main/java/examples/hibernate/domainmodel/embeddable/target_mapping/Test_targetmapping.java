package examples.hibernate.domainmodel.embeddable.target_mapping;

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

import examples.hibernate.domainmodel.embeddable.target_mapping.TargetMapping.City;
import examples.hibernate.domainmodel.embeddable.target_mapping.TargetMapping.GPS;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_targetmapping{

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;

	@Test@Disabled
	protected void placeholder() {

	}

	@Test@DisplayName("target.persist")@Order(1)
	protected void persist() {
		em.getTransaction().begin();
		City cluj = new City();
		cluj.setName( "Cluj" );
		cluj.setCoordinates( new GPS( 46.77120, 23.62360 ) );
		em.persist( cluj );
		em.getTransaction().commit();
	}

	@Test@DisplayName("target.query")@Order(2)
	protected void query()   {
		City cluj = em.find( City.class, 1L );
		assertEquals( 46.77120, cluj.getCoordinates().x(), 0.00001 );
		assertEquals( 23.62360, cluj.getCoordinates().y(), 0.00001 );
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(TargetMapping.City.class);
		meta.addAnnotatedClass(TargetMapping.GPS.class);

	}

	protected static void init_data() {




	}

	@BeforeAll
	protected static void setUp() throws Exception {

		Map<String,String> additionalSettings = new HashMap<>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS LOBS;SET SCHEMA LOBS");
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_targetmapping.addMeta(meta);
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
