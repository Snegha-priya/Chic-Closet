package com.chiccloset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

	Page<CustomerModel> findByActive(boolean active, Pageable paging);

	CustomerModel findByIdAndActive(Long id, boolean active);

	Page<CustomerModel> findByFullNameContainingIgnoreCaseOrPhoneNumberContainsAndActive(String searchString,
			String searchString2, boolean active, Pageable paging);

}