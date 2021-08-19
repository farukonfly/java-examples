package examples.springdata.jpa.entitygraph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import examples.springdata.jpa.entitygraph.entity.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Long> {

}
