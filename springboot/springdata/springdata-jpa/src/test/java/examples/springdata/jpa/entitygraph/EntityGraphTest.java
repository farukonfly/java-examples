package examples.springdata.jpa.entitygraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.TestPropertySource;

import examples.springdata.jpa.entitygraph.entity.Comment;
import examples.springdata.jpa.entitygraph.entity.Post;
import examples.springdata.jpa.entitygraph.entity.User;

@TestPropertySource(locations = "classpath:examples/springdata/jpa/entitygraph/application-test.properties")
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EntityGraphTest {


	@Configuration
	@EnableAutoConfiguration
	static class Config {
	}

	@Autowired
	private IPostRepository postRepository;
	@Autowired
	private IUserRepository userRepository;
	@Autowired
	private ICommentRepository commentRepository;

	static List<Long> postIds = new ArrayList<>() ; //test for entitygraph


	@Test
	@Order(1)
	void prepareEnvironment(){
		User u1 = new User("u1");
		User u2 = new User("u2");
		User u3 = new User("u3");

		Post p1_1 = new Post("post-u1-1", u1);
		Post p1_2 = new Post("post-u1-2", u1);

		Post p2_1 = new Post("post-u2-1", u2);
		Post p2_2 = new Post("post-u2-2", u2);

		Post p3_1 = new Post("post-u3-1", u3);
		Post p3_2 = new Post("post-u3-2", u3);

		Comment c_u1_p1_1 = new Comment("c_u1_p1_1", p1_1, u1);
		Comment c_u1_p1_2 = new Comment("c_u1_p1_2", p1_1, u1);

		Comment c_u1_p2_1 = new Comment("c_u1_p2_1", p1_2, u1);
		Comment c_u1_p2_2 = new Comment("c_u1_p2_2", p1_2, u1);

		Comment c_u2_p1_1 = new Comment("c_u2_p1_1", p2_1, u2);
		Comment c_u2_p1_2 = new Comment("c_u2_p1_2", p2_1, u2);

		Comment c_u2_p2_1 = new Comment("c_u2_p2_1", p2_2, u2);
		Comment c_u2_p2_2 = new Comment("c_u2_p2_2", p2_2, u2);

		Comment c_u3_p1_1 = new Comment("c_u3_p1_1", p3_1, u3);
		Comment c_u3_p1_2 = new Comment("c_u3_p1_2", p3_1, u3);

		Comment c_u3_p2_1 = new Comment("c_u3_p2_1", p3_2, u3);
		Comment c_u3_p2_2 = new Comment("c_u3_p2_2", p3_2, u3);


		this.userRepository.save(u1);
		this.userRepository.save(u2);
		this.userRepository.save(u3);

		this.postRepository.save(p1_1);
		this.postRepository.save(p1_2);
		this.postRepository.save(p2_1);
		this.postRepository.save(p2_2);
		this.postRepository.save(p3_1);
		this.postRepository.save(p3_2);

		this.commentRepository.save(c_u1_p1_1);
		this.commentRepository.save(c_u1_p1_2);
		this.commentRepository.save(c_u1_p2_1);
		this.commentRepository.save(c_u1_p2_2);
		this.commentRepository.save(c_u2_p1_1);
		this.commentRepository.save(c_u2_p1_2);
		this.commentRepository.save(c_u2_p2_1);
		this.commentRepository.save(c_u2_p2_2);
		this.commentRepository.save(c_u3_p1_1);
		this.commentRepository.save(c_u3_p1_2);
		this.commentRepository.save(c_u3_p2_1);
		this.commentRepository.save(c_u3_p2_2);

		postIds.addAll(Arrays.asList(p1_1.getId(),p1_2.getId()
				,p2_1.getId(),p2_2.getId()
				,p3_1.getId(),p3_2.getId()));

	}

	@Test
	@Order(2)
	void testSubEntityGraph() {
		Long seed = 1L ;
		Long postId = (long) new Random(seed).nextInt(postIds.size());
		System.out.println("postId:"+postId);
		Post post = this.postRepository.findPostSubEntityGraphById(postIds.get(0));
		System.out.println(post);
	}


}
