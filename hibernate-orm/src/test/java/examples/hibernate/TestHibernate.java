package examples.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestHibernate {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;

	@Test
	protected void test1() {
		Session s = sessionFactory.getCurrentSession();
	}

	protected static void addEntity() {
		meta.addAnnotatedClass(null);
	}

	@BeforeAll
	protected static void setUp() throws Exception {
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			TestHibernate.sessionFactory = meta.buildMetadata().buildSessionFactory();

		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}



	@AfterAll
	protected static void tearDown() {
		TestHibernate.sessionFactory.close();
	}
}
