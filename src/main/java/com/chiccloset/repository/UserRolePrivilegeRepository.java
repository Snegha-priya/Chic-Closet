package com.chiccloset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.UserRolePrivilegeModel;


@Repository
public interface UserRolePrivilegeRepository extends JpaRepository<UserRolePrivilegeModel, Long> {

	@Query(value = "select up.privilege from privileges up, user_role_privilege urp"
			+ " where up.id = urp.privilege_id and up.active = :active"
			+ " and urp.active = :active and urp.role_id = :roleId", nativeQuery = true)
	List<String> findByRoleidAndActive(long roleId, boolean active);

}