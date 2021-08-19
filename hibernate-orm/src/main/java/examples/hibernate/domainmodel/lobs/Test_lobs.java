package examples.hibernate.domainmodel.lobs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.ClobProxy;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import examples.hibernate.domainmodel.lobs.LobsEntity.Product;


public class Test_lobs{

	private static SessionFactory sessionFactory;
	private static MetadataSources meta;
	private static EntityManager em ;
	private static Session session ;

	@Test@Disabled
	protected void placeholder() {

	}

	@Test@DisplayName("lob.persist")
	protected void persist() {
		em.getTransaction().begin();
		String warranty = "My product warranty";
		final Product product = new Product();
		product.setId( 1 );
		product.setName( "Mobile phone" );
		product.setWarranty( ClobProxy.generateProxy( warranty ) );
		em.persist( product );
		em.getTransaction().commit();
	}

	@Test@DisplayName("lob.query")
	protected void query() throws IOException, SQLException {
		Product product = em.find( Product.class, 1 );
		try (Reader reader = product.getWarranty().getCharacterStream()) {
			String line = null ;
			String value = "" ;
			BufferedReader br=new BufferedReader(reader);
			while((line=br.readLine())!=null){
				value += line;
			}

			System.out.println(value.toString());
			assertEquals( "My product warranty", value);
		}
	}

	protected static void addMeta(MetadataSources meta) {
		meta.addAnnotatedClass(LobsEntity.Product.class);

	}

	protected static void init_data() {




	}

	@BeforeAll
	protected static void setUp() throws Exception {

		Map<String,String> additionalSettings = new HashMap<>();
		additionalSettings.put("hibernate.connection.INIT","CREATE SCHEMA IF NOT EXISTS LOBS;SET SCHEMA LOBS");
		// A SessionFactory is set up once for an application!
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().applySettings(additionalSettings).build();
		// configures settings from hibernate.cfg.xml
		try {
			meta = new MetadataSources(registry);
			Test_lobs.addMeta(meta);
			sessionFactory = meta.buildMetadata().buildSessionFactory();
			init_data();

		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but we had trouble
			// building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	@BeforeEach
	protected void openSession() {
		em = getJpaInterface(sessionFactory);
		session = getHibernateInterface_opensession(sessionFactory);
	}

	@AfterEach
	protected void closeSession() {
		session.close();
		em.close();
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
	protected static Session getHibernateInterface_opensession(SessionFactory sessionFactory) {
		return sessionFactory.openSession();
	}

	protected static Session getHibernateInterface_currentsession(SessionFactory sessionFactory) {
		return sessionFactory.getCurrentSession();
	}



}
