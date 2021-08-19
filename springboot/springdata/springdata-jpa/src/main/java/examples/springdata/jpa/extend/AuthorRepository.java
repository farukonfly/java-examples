package examples.springdata.jpa.extend;

import org.springframework.data.repository.CrudRepository;

import examples.springdata.jpa.extend.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long>,CustomAuthorRepository {

}
