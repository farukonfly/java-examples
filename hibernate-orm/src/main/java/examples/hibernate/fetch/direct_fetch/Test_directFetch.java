package examples.hibernate.fetch.direct_fetch;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

import examples.hibernate.fetch.direct_fetch.DirectFetchEntities.Department;
import examples.hibernate.fetch.direct_fetch.DirectFetchEntities.Employee;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_directFetch {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;

	@Test@Order(3)
	protected void jpql_fetch_join() {
		Employee employee = em.createQuery(
				"select e " +
						"from Employee e left join fetch e.department " +
						"where e.id = :id", Employee.class)
				.setParameter( "id", 1L )
				.getSingleResult();
		assertNotNull(employee.getDepartment());
		/**
		 *     select
        directfetc0_.id as id1_1_0_,
        directfetc1_.id as id1_0_1_,
        directfetc0_.department_id as departme3_1_0_,
        directfetc0_.username as username2_1_0_ 
	    from
	        Employee directfetc0_ 
	    left outer join
	        Department directfetc1_ 
	            on directfetc0_.department_id=directfetc1_.id 
	    where
	        directfetc0_.id=?
		 */
	}

	@Test@Order(2)
	protected void jpql_lead_N_1() {
		/**
		 *     select
        directfetc0_.id as id1_1_,
        directfetc0_.department_id as departme3_1_,
        directfetc0_.username as username2_1_ 
	    from
	        Employee directfetc0_ 
	    where
	        directfetc0_.id=?

	            select
	        directfetc0_.id as id1_0_0_ 
	    from
	        Department directfetc0_ 
	    where
	        directfetc0_.id=?
		 */
		Employee employee = em.createQuery(
				"select e " +
						"from Employee e " +
						"where e.id = :id", Employee.class)
				.setParameter( "id", 1L )
				.getSingleResult();
	}

	@Test@Order(1) 
	protected void direct_fetch() {
		Employee employee = em.find( Employee.class, 1L );
		/**
		 *     select
        directfetc0_.id as id1_1_0_,
        directfetc0_.department_id as departme3_1_0_,
        directfetc0_.username as username2_1_0_,
        directfetc1_.id as id1_0_1_ 
	    from
	        Employee directfetc0_ 
	    left outer join
	        Department directfetc1_ 
	            on directfetc0_.department_id=directfetc1_.id 
	    where
	        directfetc0_.id=?
		 * 
		 * 
		 * 
		 * 
		 */

	}

	@Test@Order(0)
	protected void persist() {
		em.getTransaction().begin();
		Department d = new Department(1L);
		Employee e = new Employee(1L, "e1", d);
		em.persist(d);em.persist(e);
		em.getTransaction().commit();
	}


	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(DirectFetchEntities.Department.class);
		meta.addAnnotatedClass(DirectFetchEntities.Employee.class);


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
				"CREATE SCHEMA IF NOT EXISTS fetching;SET SCHEMA fetching");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_directFetch.addMeta(meta);
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
