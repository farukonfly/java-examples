package examples.hibernate.fetch.graph_fetch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import examples.hibernate.fetch.graph_fetch.GraphFetchEntities.Department;
import examples.hibernate.fetch.graph_fetch.GraphFetchEntities.Employee;
import examples.hibernate.fetch.graph_fetch.GraphFetchEntities.Project;


public class Test_graphFetch{

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;

	@Test
	protected void entityGraph_fetch_jpa() {
		Employee employee = em.find(Employee.class, 1L,
				Collections.singletonMap("javax.persistence.fetchgraph", em.getEntityGraph("employee.projects")));
		Assertions.assertTrue(employee.getProjects().size()>0);
	}

	@Test@Disabled
	protected void criteria_fetch() {
		//会以left outer join方式进行查询,并且装配好projects
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
		Root<Employee> root = query.from(Employee.class);
		root.fetch("projects",JoinType.LEFT);
		query.select(root).where(cb.and(
				cb.equal(root.get("username"), "username"),
				cb.equal(root.get("password"), "password")));
		Employee employee = em.createQuery(query).getSingleResult();
		Assertions.assertTrue(employee.getProjects().size()>0);
	}
	@Test
	@Disabled
	protected void jpql_fetch() {
		//会以left outer join方式进行查询
		Employee employee = em.createQuery(
				"select e " +
						"from Employee e " +
						"left join fetch e.projects " +
						"where " +
						"	e.username = :username and " +
						"	e.password = :password",
						Employee.class)
				.setParameter( "username", "username")
				.setParameter( "password", "password")
				.getSingleResult();
		Assertions.assertTrue(employee.getProjects().size()>0);

	}

	@Test
	@Disabled
	protected void not_fetch_lazytoone() {
		/**
		 * 	Employee.java
		 * 	@ManyToOne(fetch = FetchType.LAZY)
			private Department department;
			关联对象fetchType都是LAZY，只会加载Employee，不会加载关联对象
		 */
		Employee employee = em.createQuery(
				"select e " +
						"from Employee e " +
						"where " +
						"	e.username = :username and " +
						"	e.password = :password",
						Employee.class)
				.setParameter( "username", "username")
				.setParameter( "password", "password")
				.getSingleResult();

		Integer accessLevel = em.createQuery(
				"select e.accessLevel " +
						"from Employee e " +
						"where " +
						"	e.username = :username and " +
						"	e.password = :password",
						Integer.class)
				.setParameter( "username", "username")
				.setParameter( "password", "password")
				.getSingleResult();

	}

	@Test
	@Disabled
	protected void jpa_fetch() {
		/**
		 * 	Employee.java
		 * 	@ManyToOne(fetch = FetchType.EAGER)
			private Department department;
		 * 会已左外连接加载关联对象 
		 */
		Employee employee = em.find( Employee.class, 1L );
		System.out.println(employee);


	}

	@Test
	@Disabled
	protected void hibernate_fetch() {
		/**
		 * 	Employee.java
		 * 	@ManyToOne(fetch = FetchType.EAGER)
			private Department department;
		 * 会已左外连接加载关联对象 
		 */
		Employee employee = session.find(Employee.class, 1L);
		System.out.println(employee);
	}

	@Test
	@Disabled
	protected void query_nplus1() {
		/**
		 * 	Employee.java
		 * 	@ManyToOne(fetch = FetchType.EAGER)
			private Department department;
		 * 不会已左外连接加载关联对象，会出现n+1问题
		 */
		Employee employee = em.createQuery(
				"select e " +
						"from Employee e " +
						"where e.id = :id", Employee.class)
				.setParameter( "id", 1L )
				.getSingleResult();
		System.out.println(employee);
		/**
		 *  select
	            employee0_.id as id1_1_,
	            employee0_.department_id as departme3_1_,
	            employee0_.username as username2_1_ 
	        from
	            h11_fetch.Employee employee0_ 
	        where
	            employee0_.id=?
		 * 
		 * 
		 *   select
		        department0_.id as id1_0_0_ 
		    from
		        h11_fetch.Department department0_ 
		    where
		        department0_.id=?
		 * 
		 */
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(Department.class);
		meta.addAnnotatedClass(Employee.class);
		meta.addAnnotatedClass(Project.class);

	}

	protected static void init_data() {
		em.getTransaction().begin();
		Department d = new Department(1L);
		Project p = new Project(1L);
		em.persist(d);
		Employee e = new Employee(1L);
		e.setUsername("username");
		e.setPassword("password");
		e.setDepartment(d);
		em.persist(e);
		p.getEmployees().add(e);
		em.persist(p);
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
				"CREATE SCHEMA IF NOT EXISTS fetching;SET SCHEMA fetching");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_graphFetch.addMeta(meta);
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
		return em ;

	}

	protected static Session getHibernateInterface_currentsession(SessionFactory sessionFactory) {
		return sessionFactory.getCurrentSession();
	}

	protected static Session getHibernateInterface_opensession(SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}

}
