package examples.hibernate.domainmodel.identifiers.mpasid;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import examples.hibernate.domainmodel.identifiers.mpasid.MapsIdEntities.Person;
import examples.hibernate.domainmodel.identifiers.mpasid.MapsIdEntities.PersonDetails;


public class Test_mapsid2{

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;

	@Test
	protected void test_MapsIdEntities() {
		PersonDetails personDetails = em.find( PersonDetails.class, 1L );
		System.out.println("pauser");

	}

	protected static void init_data() {
		em.getTransaction().begin();
		Person person = new Person( "ABC-123" );
		person.setId( 1L );
		em.persist( person );

		PersonDetails personDetails = new PersonDetails();
		personDetails.setNickName( "John Doe" );
		personDetails.setPerson( person );

		em.persist( personDetails );

		em.getTransaction().commit();
		em.detach(person);em.detach(personDetails);
	}

	protected static void addMeta(MetadataSources meta) {
		//MapsIdEntities
		meta.addAnnotatedClass(Person.class);
		meta.addAnnotatedClass(PersonDetails.class);

		//MpasIdEntities2
		//meta.addAnnotatedClass(Employee.class);
		//meta.addAnnotatedClass(Dependent.class);


	}

	@BeforeAll
	protected static void setUp() throws Exception {
		Map<String,String> additionalSettings = new HashMap<>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS mapsid;SET SCHEMA mapsid");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			addMeta(meta);
			sessionFactory = meta.buildMetadata().buildSessionFactory();
			em = getJpaInterface(sessionFactory);
			session = getHibernateInterface_opensession(sessionFactory);
			init_data();

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
		em.close();
		session.close();
		sessionFactory.close();
	}

	protected static EntityManager getJpaInterface(SessionFactory sessionFactory) {
		Session session = getHibernateInterface_opensession(sessionFactory);
		EntityManager em = session.getEntityManagerFactory().createEntityManager();
		return em ;

	}

	protected static Session getHibernateInterface_currentsession(SessionFactory sessionFactory) {
		return sessionFactory.getCurrentSession();
	}

	protected static  Session getHibernateInterface_opensession(SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}

}
