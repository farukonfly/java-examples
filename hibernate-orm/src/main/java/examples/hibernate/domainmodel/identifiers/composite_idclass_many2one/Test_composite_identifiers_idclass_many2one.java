package examples.hibernate.domainmodel.identifiers.composite_idclass_many2one;

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

public class Test_composite_identifiers_idclass_many2one {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;

	@Test
	final void  placeholder() {

	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(CompositeIdClass_Many2One.SystemUser.class);
		meta.addAnnotatedClass(CompositeIdClass_Many2One.Subsystem.class);

	}

	@BeforeAll
	protected static void setUp() throws Exception {
		Map<String,String> additionalSettings = new HashMap<>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS IdClass_Many2One;SET SCHEMA IdClass_Many2One");
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_composite_identifiers_idclass_many2one.addMeta(meta);
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
