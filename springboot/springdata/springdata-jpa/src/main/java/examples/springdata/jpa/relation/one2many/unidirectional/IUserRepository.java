package examples.springdata.jpa.relation.one2many.unidirectional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Integer>{
    
}

