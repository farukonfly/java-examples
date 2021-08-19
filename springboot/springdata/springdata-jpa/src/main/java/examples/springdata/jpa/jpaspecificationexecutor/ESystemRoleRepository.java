package examples.springdata.jpa.jpaspecificationexecutor;

import java.util.Optional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import examples.springdata.jpa.jpaspecificationexecutor.entity.ESystemRole;

public interface ESystemRoleRepository extends JpaRepository<ESystemRole, Long>,JpaSpecificationExecutor<ESystemRole> {


	/*
	   select
        count(esystemrol0_.`id`) as col_0_0_ 
	    from
	        system_role esystemrol0_ 
	    inner join
	        farukon_role erole1_ 
	            on esystemrol0_.`authorize_role_id`=erole1_.`id` 
	    where
	        erole1_.rolecode=? 
	        and esystemrol0_.`id`<>1 
	 */
	default long countByRolecode_1(String rolecode, Long excludeId) {
		return this.count((root, query, criteriaBuilder) -> {
			if (excludeId != null) {
				// 更新
				//	root.join("authorizeRole")

				//Predicate equals_name = criteriaBuilder.equal(root.get("authorizeRole").get("rolecode"), rolecode);  
				//root.get("authorizeRole") 会生成cross join 性能差 

				Predicate equals_name = criteriaBuilder.equal(root.join("authorizeRole").get("rolecode"), rolecode); //root.join("authorizeRole") 生成inner join

				Predicate notEqual_id = criteriaBuilder.notEqual(root.get("id"), excludeId);
				return criteriaBuilder.and(equals_name, notEqual_id);
			}
			return criteriaBuilder.equal(root.join("authorizeRole").get("rolecode"), rolecode);
		});
	}

	default Optional<ESystemRole> findOne_1(String rolecode) {
		return this.findOne((root, query, criteriaBuilder) -> {
			System.out.println("debug");

			//return criteriaBuilder.equal(root.join("authorizeRole").get("rolecode"), rolecode);
			//return criteriaBuilder.equal(root.get("rolecode"), rolecode);
			Join<Object, Object> joinTable =  (Join<Object, Object>)root.fetch("authorizeRole"); 
			//	Join<Object, Object> joinTable2 =  (Join<Object, Object>)joinTable.fetch("resources"); 

			return criteriaBuilder.equal(joinTable.get("rolecode"), rolecode);
			//root.fetch("authorizeRole");
			//return criteriaBuilder.equal(root.get("rolecode"), rolecode);
		});
	}
}
