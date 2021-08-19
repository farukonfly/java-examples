package examples.hibernate.batching.naive_batch;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import examples.hibernate.batching.naive_batch.NaiveBatchEntities.Person;


//Filters apply to entity queries, but not to direct fetching.
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_naive_batch {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;



	@Test@Order(2) 
	protected void size_batch_persist() {
		EntityTransaction txn = em.getTransaction();
		int batchSize = 25;
		try {
			txn.begin();
			for ( int i = 0; i < 100_000; i++ ) {
				if ( i > 0 && i % batchSize == 0 ) {
					//flush a batch of inserts and release memory
					em.flush();
					em.clear();
				}
				Person Person = new Person( String.format( "Person-batchSize %d", i ) );
				em.persist( Person );
			}
			txn.commit();
		} catch (RuntimeException e) {
			if(txn!=null&&txn.isActive()) {
				txn.rollback();
			}
			throw e ;
		}
	}

	@Test@Order(1)@Disabled
	protected void naive_batch_persist() {
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();
			for ( int i = 0; i < 100_000; i++ ) {
				Person Person = new Person( String.format( "Person %d", i ) );
				em.persist( Person );
			}
			txn.commit();
		} catch (RuntimeException e) {
			if(txn!=null&&txn.isActive()) {
				txn.rollback();
			}
			throw e ;
		}
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(NaiveBatchEntities.Person.class);


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
				"CREATE SCHEMA IF NOT EXISTS batching;SET SCHEMA batching");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_naive_batch.addMeta(meta);
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
