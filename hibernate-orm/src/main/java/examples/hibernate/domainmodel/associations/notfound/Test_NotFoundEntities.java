package examples.hibernate.domainmodel.associations.notfound;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import examples.hibernate.domainmodel.associations.notfound.NotFoundEntities.City;
import examples.hibernate.domainmodel.associations.notfound.NotFoundEntities.Person;



public class Test_NotFoundEntities {
	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;


	@Test
	protected void load() {
		Person person = em.find( Person.class, 1L );
		//assertEquals( "New York", person.getCity().getName() );
		assertEquals( "Atlantis", person.getCityName() );
		assertNull(person.getCity());
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(NotFoundEntities.City.class);
		meta.addAnnotatedClass(NotFoundEntities.Person.class);


	}

	protected static void init_data() {
		em.getTransaction().begin();
		City _NewYork = new City();
		_NewYork.setName( "New York" );
		em.persist( _NewYork );

		Person person = new Person();
		person.setId( 1L );
		person.setName( "John Doe" );
		//person.setCityName( "New York" ); 
		person.setCityName( "Atlantis" );
		em.persist( person );
		em.getTransaction().commit();
		em.detach(_NewYork);em.detach(person);



	}

	@BeforeAll
	protected static void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
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

	protected static Session getHibernateInterface_opensession(SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}
}
