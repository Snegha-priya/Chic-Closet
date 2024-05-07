package com.chiccloset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiccloset.dto.OrderDTO;
import com.chiccloset.service.OrderService;


@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	// save
	@PostMapping("/ordersave")
	public ResponseEntity<String> save(@RequestBody OrderDTO orderDTOList) {
		String response = orderService.save(orderDTOList);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "/orderremove")
	public ResponseEntity<String> remove(@RequestParam Long id) {
		String response = orderService.remove(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// list

	@GetMapping("/orderlist")
	public ResponseEntity<List<OrderDTO>> list() {
		List<OrderDTO> contactUsDTOList = orderService.list();
		return ResponseEntity.ok(contactUsDTOList);
	}

}