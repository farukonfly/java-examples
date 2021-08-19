package examples.hibernate.domainmodel.inheritance.singletable;

import java.math.BigDecimal;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import examples.hibernate.domainmodel.inheritance.singletable.SingleTableEntities.CreditAccount;
import examples.hibernate.domainmodel.inheritance.singletable.SingleTableEntities.DebitAccount;

public class Test_inheritance_singletable {

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;

	@Test@DisplayName("Inheritance_singleTable")@Order(1)
	protected void persist() {
		em.getTransaction().begin();
		DebitAccount debitAccount = new DebitAccount();
		debitAccount.setId( 1L );
		debitAccount.setOwner( "John Doe" );
		debitAccount.setBalance( BigDecimal.valueOf( 100 ) );
		debitAccount.setInterestRate( BigDecimal.valueOf( 1.5d ) );
		debitAccount.setOverdraftFee( BigDecimal.valueOf( 25 ) );

		CreditAccount creditAccount = new CreditAccount();
		creditAccount.setId( 2L );
		creditAccount.setOwner( "John Doe" );
		creditAccount.setBalance( BigDecimal.valueOf( 1000 ) );
		creditAccount.setInterestRate( BigDecimal.valueOf( 1.9d ) );
		creditAccount.setCreditLimit( BigDecimal.valueOf( 5000 ) );

		em.persist( debitAccount );
		em.persist( creditAccount );
		em.getTransaction().commit();
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(SingleTableEntities.Account.class);
		meta.addAnnotatedClass(SingleTableEntities.CreditAccount.class);
		meta.addAnnotatedClass(SingleTableEntities.DebitAccount.class);

	}


	@BeforeEach
	protected void openSession() {
		em = getJpaInterface(sessionFactory);
		session = getHibernateInterface_opensession(sessionFactory);
	}

	@AfterEach
	protected void closeSession() {
		if(session.isOpen()) {
			session.close();
		}
		if(em.isOpen()) {
			em.close();
		}
	}

	@BeforeAll
	protected static void setUp() throws Exception {
		Map<String,String> additionalSettings = new HashMap<String,String>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS inherit_singletable;SET SCHEMA inherit_singletable");

		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_inheritance_singletable.addMeta(meta);
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
		return em ;

	}

	protected static  Session getHibernateInterface_currentsession(SessionFactory sessionFactory) {
		return sessionFactory.getCurrentSession();
	}

	protected static Session getHibernateInterface_opensession(SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}

}
