package examples.hibernate.domainmodel.identifiers.idgenerator.randomUUID;

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

public class Test_identifiers_randomUUID {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;

	@Test
	final void  placeholder() {

	}


	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(RandomUUIDEntities.Book.class);

	}

	protected static void init_data() {

	}

	@BeforeAll
	protected static void setUp() throws Exception {
		Map<String,String> additionalSettings = new HashMap<>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS identifiers_randomuuid;SET SCHEMA identifiers_randomuuid");
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_identifiers_randomUUID.addMeta(meta);
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
