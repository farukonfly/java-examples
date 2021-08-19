package examples.springdata.jpa.extend;

import java.util.List;

public interface CustomAuthorRepository {
	List<AuthorSummaryDTO> getAuthorsByFirstName(String firstName);
}
