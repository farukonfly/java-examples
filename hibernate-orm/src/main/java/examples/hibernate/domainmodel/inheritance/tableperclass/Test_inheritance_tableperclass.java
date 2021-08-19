package examples.hibernate.domainmodel.inheritance.tableperclass;

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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import examples.hibernate.domainmodel.inheritance.tableperclass.TablePerClass.Account;

public class Test_inheritance_tableperclass {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;

	@Test@DisplayName("Inheritance_persist")@Order(1)
	protected void persist() {
		//	List<Account> accounts = em.createQuery("select a from Account a", Account.class).getResultList();
	}

	@Test@DisplayName("Inheritance_query")@Order(2)
	protected void polymorphic() {
		List<Account> accounts = em.createQuery( "select a from Account a" ).getResultList();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(TablePerClass.Account.class);
		meta.addAnnotatedClass(TablePerClass.CreditAccount.class);
		meta.addAnnotatedClass(TablePerClass.DebitAccount.class);

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
				"CREATE SCHEMA IF NOT EXISTS inherit_tableperclass;SET SCHEMA inherit_tableperclass");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_inheritance_tableperclass.addMeta(meta);
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
