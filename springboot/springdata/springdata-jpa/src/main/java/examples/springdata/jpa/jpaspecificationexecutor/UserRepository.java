package examples.springdata.jpa.jpaspecificationexecutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import examples.springdata.jpa.jpaspecificationexecutor.entity.User;

public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

}
