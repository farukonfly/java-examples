package examples.hibernate.domainmodel.identifiers.idgenerator.rowid;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class Test_idgenerator_rowid{

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;

	@Test
	protected void test_PrimaryKeyJoinColumn() {
		Session session = this.getHibernateInterface_opensession(sessionFactory);
		session.beginTransaction();
		Product p1= new Product(1L, "name", "number");
		session.save(p1);
		session.getTransaction().commit();
		session.evict(p1);
		session.find(Product.class, 1L);
		session.close();



	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(Product.class);

	}

	@BeforeAll
	protected static void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_idgenerator_rowid.addMeta(meta);
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
