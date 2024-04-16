package com.chiccloset.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.ConfigServiceModel;


@Repository
public interface ConfigServiceRepository extends JpaRepository<ConfigServiceModel, Long> {
	@Query(value = "SELECT serviceurl FROM config_service where active =:active and servicename = :servicename and servicemethod = :servicemethod", nativeQuery = true)
	String getServiceURL(boolean active, String servicename, String servicemethod);
}
