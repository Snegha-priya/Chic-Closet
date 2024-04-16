package com.chiccloset.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiccloset.entitymodel.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {

	ProductModel findByIdAndActive(Long id, boolean active);

}