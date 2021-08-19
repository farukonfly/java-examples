package examples.springdata.jpa.extend;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import examples.springdata.jpa.extend.entity.Author;

public class CustomAuthorRepositoryImpl implements CustomAuthorRepository {

	private @Autowired EntityManager em ;

	@Override
	public List<AuthorSummaryDTO> getAuthorsByFirstName(String firstName) {
		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<AuthorSummaryDTO> query = cb.createQuery(AuthorSummaryDTO.class);
		Root<Author> root = query.from(Author.class);
		query.select(cb.construct(AuthorSummaryDTO.class, root.get("firstName"),root.get("lastName")))
		.where(cb.equal(root.get("firstName"),firstName));
		List<AuthorSummaryDTO> list =  this.em.createQuery(query).getResultList();
		return list ;
	}

}
