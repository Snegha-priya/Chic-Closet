package com.chiccloset.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chiccloset.entitymodel.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel, Long> {

	CategoryModel findByIdAndActive(Long id, boolean active);

}
