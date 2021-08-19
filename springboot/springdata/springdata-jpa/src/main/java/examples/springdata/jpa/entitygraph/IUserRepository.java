package examples.springdata.jpa.entitygraph;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import examples.springdata.jpa.entitygraph.entity.User;

public interface IUserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<Long> {

}
