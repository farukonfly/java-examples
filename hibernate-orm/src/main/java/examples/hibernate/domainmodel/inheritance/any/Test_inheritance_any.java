package examples.hibernate.domainmodel.inheritance.any;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import examples.hibernate.domainmodel.inheritance.any.AnyEntities.ChessPlayer;
import examples.hibernate.domainmodel.inheritance.any.AnyEntities.MonopolyPlayer;
import examples.hibernate.domainmodel.inheritance.any.AnyEntities.PlayerScore;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test_inheritance_any {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em;
	private static Session session;

	@Test@DisplayName("Inheritance_polymorphicquery")@Order(2)
	protected void polymorphicquery() {
		String mp_firstName = "mp";String cp_firstName="cp";
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PlayerScore> criteriaQuery = cb.createQuery(PlayerScore.class);
		Root<PlayerScore> root = criteriaQuery.from(PlayerScore.class);
		criteriaQuery.select(root).where(cb.equal(root.get("player").get("firstName"),mp_firstName));
		em.createQuery(criteriaQuery).getResultList();
	}

	@Test@DisplayName("Inheritance_polymorphicpersist")@Order(1)
	protected void persist() {
		em.getTransaction().begin();
		MonopolyPlayer mp = new MonopolyPlayer(); mp.setFirstName("mp");
		ChessPlayer cp = new ChessPlayer(); cp.setFirstName("cp");
		PlayerScore ps1 = new PlayerScore();
		PlayerScore ps2 = new PlayerScore();
		ps1.setPlayer(mp);ps2.setPlayer(cp);
		em.persist(mp);em.persist(cp);
		em.persist(ps1);em.persist(ps2);
		em.getTransaction().commit();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addPackage("examples.hibernate.domainmodel.inheritance.any");
		meta.addAnnotatedClass(ChessPlayer.class);
		meta.addAnnotatedClass(MonopolyPlayer.class);
		meta.addAnnotatedClass(PlayerScore.class);


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
				"CREATE SCHEMA IF NOT EXISTS inherit_polymorphic;SET SCHEMA inherit_polymorphic");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure()
				.applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_inheritance_any.addMeta(meta);
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
