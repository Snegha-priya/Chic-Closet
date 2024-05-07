package com.chiccloset.service;

import java.util.List;

import com.chiccloset.dto.OrderDTO;


public interface OrderService {
	String save(OrderDTO orderDTO);

	String remove(Long id);

	List<OrderDTO> list();
}