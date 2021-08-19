package examples.hibernate.batching.bulk_id;

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

import examples.hibernate.batching.bulk_id.BulkIdEntities.Person;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_bulk_id {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;

	@Test@Order(2)
	protected void delete_from_parent_class() {
		session.beginTransaction();
		int updateCount = session.createQuery(
				"delete from Person where employed = :employed" )
				.setParameter( "employed", false )
				.executeUpdate(); //默认会把Person表的所有ID存入临时表
		session.getTransaction().commit();

	}

	@Test@Order(1)
	protected void persist() {
		em.getTransaction().begin();
		Person p1 = new Person( 1,"Red Hat USA");
		Person p2 = new Person( 3,"Red Hat USA" );
		Person p3 = new Person( 1,"Red Hat Europe");
		Person p4 = new Person( 3,"Red Hat Europe");
		em.persist(p1);em.persist(p2);em.persist(p3);em.persist(p4);
		em.getTransaction().commit();
	}


	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(BulkIdEntities.Person.class);
		meta.addAnnotatedClass(BulkIdEntities.Doctor.class);
		meta.addAnnotatedClass(BulkIdEntities.Engineer.class);
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
		//additionalSettings.put("hibernate.hql.bulk_id_strategy","org.hibernate.hql.spi.id.inline.InlineIdsInClauseBulkIdStrategy");
		//The IN clause row value expression has long been supported by Oracle, PostgreSQL, and nowadays by MySQL 5.7. 
		//However, SQL Server 2014 does not support it, so you’ll have to use a different strategy.
		//additionalSettings.put("hibernate.hql.bulk_id_strategy","org.hibernate.hql.spi.id.inline.InlineIdsSubSelectValueListBulkIdStrategy");
		//additionalSettings.put("hibernate.hql.bulk_id_strategy","org.hibernate.hql.spi.id.inline.InlineIdsOrClauseBulkIdStrategy");
		//additionalSettings.put("hibernate.hql.bulk_id_strategy","org.hibernate.hql.spi.id.inline.CteValuesListBulkIdStrategy");


		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_bulk_id.addMeta(meta);
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
