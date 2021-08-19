package examples.hibernate.persistencecontext.flushing.refresh;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Statement;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import examples.hibernate.persistencecontext.flushing.refresh.RefreshEntities.Person;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Test_refresh {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;



	@Test@Order(3)
	protected void refresh_entity_hiberate() {
		Person person = session.byId(Person.class).load(1L);
		session.doWork(connection->{
			try(Statement statement = connection.createStatement()){
				statement.executeUpdate("UPDATE `Person` SET name = UPPER(name)");
			}
		});
		session.refresh( person ); //会发Select语句与数据库同步
		assertEquals("JOHN DOE", person.getName() );
	}

	@Test@Order(2)@Disabled
	protected void refresh_entity_jpa() {
		em.getTransaction().begin();
		Person person = em.find(Person.class, 1L);
		em.createQuery("update Person set name= UPPER(name)" ).executeUpdate();
		em.refresh(person); //会自动提交事务
		assertEquals("JOHN DOE", person.getName() );
	}

	@Test@Order(1) 
	protected void persist() {

		em.getTransaction().begin();
		Person p1 = new Person();
		p1.setId(1L);p1.setName("john doe");
		em.persist(p1);
		em.getTransaction().commit();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(RefreshEntities.Person.class);


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
				"CREATE SCHEMA IF NOT EXISTS flushing;SET SCHEMA flushing");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_refresh.addMeta(meta);
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
