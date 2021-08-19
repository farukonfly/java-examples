package examples.springdata.jpa.entitygraph;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import examples.springdata.jpa.entitygraph.entity.Post;

public interface IPostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Long> {
	@EntityGraph("post-entity-graph")
	Post findPostEntityGraphById(Long id);

	@EntityGraph("post-entity-graph-with-comment-users")
	Post findPostSubEntityGraphById(Long id);

	@Query("select new Post(id,subject) from Post where id = :id")
	Post findPostNoEntityGraphById(Long id);


}
