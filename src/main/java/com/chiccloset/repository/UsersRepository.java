package com.chiccloset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.UsersModel;



@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Long> {

	UsersModel findByMobileNumberAndActive(String mobileNumber, boolean active);

	UsersModel findByEmail(String email);

}