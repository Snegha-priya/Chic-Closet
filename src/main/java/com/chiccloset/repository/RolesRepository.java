package com.chiccloset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.RolesModel;


@Repository
public interface RolesRepository extends JpaRepository<RolesModel, Long> {

	RolesModel findByIdAndActive(long id, boolean active);

	@Query(value = "select role from roles where id =:id and active =:active", nativeQuery = true)
	String getRoleName(@Param("id") long id, @Param("active") boolean active);

	RolesModel findByRoleEqualsIgnoreCaseAndActive(String role, boolean active);
}
