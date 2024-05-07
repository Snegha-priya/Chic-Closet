package com.chiccloset.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.AddressModel;


@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {
	
}
