package com.chiccloset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.AuthenticationToken;
import com.chiccloset.entitymodel.UsersModel;


@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findByUser(UsersModel user);
    AuthenticationToken findByToken(String token);
}
