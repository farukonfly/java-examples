package examples.hibernate.fetch.graph_program;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


public class Test_graph_program{

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;



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
		em.detach(e);em.detach(d);

	}

	@BeforeAll
	protected static void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_graph_program.addMeta(meta);
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
