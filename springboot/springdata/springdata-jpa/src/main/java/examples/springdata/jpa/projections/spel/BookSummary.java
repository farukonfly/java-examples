package examples.springdata.jpa.projections.spel;

import org.springframework.beans.factory.annotation.Value;

public interface BookSummary {

	@Value("#{target.title + '-' +target.author.firstName}") //lead to n+1
	String getBookNameAndAuthorName();

}
