package com.chiccloset.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chiccloset.entitymodel.OrderItemModel;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemModel, Long> {

	List<OrderItemModel> findByOrderid(Long id);


}