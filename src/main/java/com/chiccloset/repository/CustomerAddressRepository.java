package com.chiccloset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.CustomerAddressModel;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressModel, Long> {

	List<CustomerAddressModel> findByCustomerIdAndActive(Long cusid, boolean active);

	List<CustomerAddressModel> findByActiveAndId(boolean active, Long id);

}
