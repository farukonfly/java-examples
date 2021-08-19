package examples.springdata.jpa.extend;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestExtend {
	private @Autowired  AuthorRepository authorRepository ;

	@Test
	void projections_cutomerType(){
		List<AuthorSummaryDTO> dtos =this.authorRepository.getAuthorsByFirstName("");

	}







	@Configuration
	@EnableAutoConfiguration
	static class Config {
	}
}
