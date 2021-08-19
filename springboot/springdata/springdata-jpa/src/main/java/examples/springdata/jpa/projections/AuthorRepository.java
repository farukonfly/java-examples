package examples.springdata.jpa.projections;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import examples.springdata.jpa.extend.entity.Author;
import examples.springdata.jpa.projections.dto.AuthorSummaryDTO;
import examples.springdata.jpa.projections.dto.AuthorView;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	@Query("SELECT new examples.springdata.jpa.projections.dto.AuthorSummaryDTO(a.firstName,a.lastName) FROM Author a")
	List<AuthorSummaryDTO> findByFirstName(String fristName);

	AuthorView findViewByFirstName(String firstName); //有n+1问题

	@Query("SELECT a FROM  Author a left join fetch a.books")
	List<Author> getAuthorsAndBooks();
	<T>  T findByLastName(String lastName,Class<T> type);
}
