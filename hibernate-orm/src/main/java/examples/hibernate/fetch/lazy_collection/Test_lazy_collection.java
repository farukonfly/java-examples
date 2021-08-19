package examples.hibernate.fetch.lazy_collection;

import java.text.MessageFormat;
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
import org.junit.jupiter.api.Test;

import examples.hibernate.fetch.lazy_collection.LazyCollectionEntities.Department;
import examples.hibernate.fetch.lazy_collection.LazyCollectionEntities.Employee;

class Test_lazy_collection {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;

	@Test
	protected void test_lazy_collection() {
		Department department = em.find(Department.class, 1L);
		int employeeCount = department.getEmployees().size();

		for(int i = 0; i < employeeCount; i++ ) {
			System.out.println(MessageFormat.format("Fetched employee: {0}", department.getEmployees().get( i ).getUsername()));
		}
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(LazyCollectionEntities.Department.class);
		meta.addAnnotatedClass(LazyCollectionEntities.Employee.class);

	}

	protected static void init_data() {
		em.getTransaction().begin();
		Department department = new Department();
		department.setId( 1L );
		em.persist( department );

		for (long i = 1; i <= 3; i++ ) {
			Employee employee = new Employee();
			employee.setId( i );
			employee.setUsername( String.format( "user_%d", i ) );
			department.addEmployee(employee);

		}
		em.getTransaction().commit();

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
				"CREATE SCHEMA IF NOT EXISTS lazy_collection;SET SCHEMA lazy_collection");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_lazy_collection.addMeta(meta);
			sessionFactory = meta.buildMetadata().buildSessionFactory();
			em = getJpaInterface(sessionFactory);
			session = getHibernateInterface_opensession(sessionFactory);
			init_data();

		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
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
