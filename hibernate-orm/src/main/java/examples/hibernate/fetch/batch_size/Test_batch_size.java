package examples.hibernate.fetch.batch_size;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

import examples.hibernate.fetch.batch_size.BatchSizeEntities.Department;
import examples.hibernate.fetch.batch_size.BatchSizeEntities.Employee;

class Test_batch_size {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;

	/**
	 * @see Employee
	 */
	@Test
	protected void fetch_batch_size() {
		List<Department> departments = em.createQuery(
				"select d " +
						"from Department d " +
						"inner join d.employees e " +
						"where e.name like :p1", Department.class).setParameter("p1", "e%")
				.getResultList();
		for ( Department department : departments ) {
			System.out.println(MessageFormat.format("Department {0} has {1} employees", department.getId(),department.getEmployees().size()));

		}

	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(Department.class);
		meta.addAnnotatedClass(Employee.class);

	}

	protected static void init_data() {
		em.getTransaction().begin();
		Employee e1 = new Employee(1L, "e1");
		Employee e2 = new Employee(2L, "e2");
		Employee e3 = new Employee(3L, "e3");
		Employee e4 = new Employee(4L, "e4");
		Employee e5 = new Employee(5L, "e5");
		List<Employee> employees_1 = new ArrayList<Employee>(Arrays.asList(e1));
		List<Employee> employees_2 = new ArrayList<Employee>(Arrays.asList(e2));
		List<Employee> employees_3 = new ArrayList<Employee>(Arrays.asList(e3));
		List<Employee> employees_4 = new ArrayList<Employee>(Arrays.asList(e4));
		List<Employee> employees_5 = new ArrayList<Employee>(Arrays.asList(e5));

		Department d1 = new Department(1L);
		d1.setEmployees(employees_1);
		Department d2 = new Department(2L);
		d2.setEmployees(employees_2);
		Department d3 = new Department(3L);
		d3.setEmployees(employees_3);
		Department d4 = new Department(4L);
		d4.setEmployees(employees_4);
		Department d5 = new Department(5L);
		d5.setEmployees(employees_5);

		em.persist(d1);em.persist(d2);em.persist(d3);em.persist(d4);em.persist(d5);
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
				"CREATE SCHEMA IF NOT EXISTS batch_size;SET SCHEMA batch_size");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_batch_size.addMeta(meta);
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
