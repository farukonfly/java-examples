package examples.hibernate.domainmodel.associations.secondarytable;

import java.util.HashMap;
import java.util.List;
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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import examples.hibernate.domainmodel.associations.secondarytable.entity.ESystemRole;
import examples.hibernate.domainmodel.associations.secondarytable.entity.ESystemRole_;


public class Test_secondaryTable{

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;

	@Test
	protected void placeholder() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ESystemRole> criteria = builder.createQuery( ESystemRole.class );
		Root<ESystemRole> root = criteria.from(ESystemRole.class);
		criteria.select(root);
		criteria.where(builder.equal(root.get(ESystemRole_.rolecode),"rolecode-1"));
		List<ESystemRole> list = em.createQuery( criteria ).getResultList(); 
		System.out.println(list);

	}

	protected static void addMeta(MetadataSources meta) {
		//meta.addAnnotatedClass(AbstractEntityObject.class);
		//meta.addAnnotatedClass(EResource.class);
		//meta.addAnnotatedClass(ERole.class);
		meta.addAnnotatedClass(ESystemRole.class);

	}

	protected static void init_data() {
		em.getTransaction().begin();
		ESystemRole systemRole = new ESystemRole("rolecode-1", "rolename-1");
		em.persist(systemRole);
		em.getTransaction().commit();
	}

	@BeforeAll
	protected static void setUp() throws Exception {

		Map<String,String> additionalSettings = new HashMap<>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS secondtable;SET SCHEMA secondtable");
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);

			addMeta(meta);
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
		session.close();
		em.close();
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
