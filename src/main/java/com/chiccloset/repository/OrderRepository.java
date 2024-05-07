package com.chiccloset.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiccloset.entitymodel.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

	OrderModel findByIdAndActive(Long id, boolean b);

	List<OrderModel> findByActive(boolean b);

	//List<OrderModel> findAllByActive(boolean b);

	
}