package examples.springdata.jpa.jpaspecificationexecutor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

import examples.springdata.jpa.jpaspecificationexecutor.entity.ESystemRole;
import examples.springdata.jpa.jpaspecificationexecutor.entity.User;

@TestPropertySource(locations = "classpath:examples/springdata/jpa/jpaspecificationexecutor/application-test.properties")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JpaSpecifactionExecutorTest {

	@Autowired  UserRepository repository;
	@Autowired ESystemRoleRepository eSystemUserRepository ;
	@Autowired EntityManager em ;

	@Configuration
	@EnableAutoConfiguration
	static class Config {
	}

	@Test
	@Disabled
	void specifation_1() {
		//System.out.println(this.eSystemUserRepository.countByRolecode_1("admin", 1L));
		//System.out.println(this.eSystemUserRepository.countByRolecode_1("admin", null));

	}

	@Test
	void findOne_1() {
		ESystemRole role =  this.eSystemUserRepository.findOne_1("admin").get();
		System.out.println("pause");
	}

	@Test
	@Order(1)
	@Disabled
	final void init() {
		this.repository.deleteAll();
		User user1 = new User();
		user1.setName("张三");

		User user2 = new User();
		user2.setName("张三三");
		this.repository.save(user1);
		this.repository.save(user2);
	}

	@Test
	@Order(2)
	@Disabled
	final void testSpecification() {
		/**
		 * CriteriaBuilder接口用来构建Predicate，
		 * 而Predicate接口用来连接子句，每次添加到predicate之前都要进行参数非空判断。
		 */
		List<User> list = this.repository.findAll((root, query, criteriaBuilder) -> {
			List<Predicate> pList = new ArrayList<>();
			pList.add(criteriaBuilder.like(root.get("name"), "%张三%"));
			//return query.where(pList.toArray(new Predicate[pList.size()])).getRestriction();
			return criteriaBuilder.and(pList.get(0));
		});

		list.forEach(user->System.out.println(user));
	}

}
