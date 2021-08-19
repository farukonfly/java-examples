package examples.hibernate.domainmodel.generated;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
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

import examples.hibernate.domainmodel.generated.GeneratedEntities.Person;


public class Test_generated{

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;

	@Test@Disabled
	protected void placeholder() {

	}

	@Test@DisplayName("generated.persist")
	protected void persist() {
		Person person = new Person();
		person.setId( 1L );
		person.setFirstName( "John" );
		person.setMiddleName1( "Flávio" );
		person.setMiddleName2( "André" );
		person.setMiddleName3( "Frederico" );
		person.setMiddleName4( "Rúben" );
		person.setMiddleName5( "Artur" );
		person.setLastName( "Doe" );
		em.persist( person );
		em.flush();

		assertEquals("John Flávio André Frederico Rúben Artur Doe", person.getFullName());
	}

	@Test@DisplayName("generated.query")
	protected void query() throws IOException, SQLException {

	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(GeneratedEntities.Person.class);

	}

	protected static void init_data() {




	}

	@BeforeAll
	protected static void setUp() throws Exception {

		Map<String,String> additionalSettings = new HashMap<>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS Generated;SET SCHEMA Generated");
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_generated.addMeta(meta);
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
