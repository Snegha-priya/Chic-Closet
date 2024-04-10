package com.chiccloset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiccloset.dto.CustomerDTO;
import com.chiccloset.dto.CustomerListDTO;
import com.chiccloset.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	// save
	@PostMapping(value = "/customersave")
	public ResponseEntity<String> save(@RequestBody CustomerDTO customerDTO) {
		String response = customerService.save(customerDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// get
	@GetMapping(value = "/customerget")
	public ResponseEntity<CustomerDTO> register(@RequestParam Long id) {
		CustomerDTO response = customerService.get(id);
		return new ResponseEntity<CustomerDTO>(response, HttpStatus.OK);
	}

	// list
	@PostMapping("/customerlist")
	public ResponseEntity<CustomerListDTO> list(@RequestBody CustomerListDTO customerListDTO) {
		customerListDTO = customerService.list(customerListDTO);
		return new ResponseEntity<CustomerListDTO>(customerListDTO, HttpStatus.CREATED);
	}

	// disable
	@DeleteMapping(value = "/customerdisable")
	public ResponseEntity<String> disable(@RequestParam Long id) {
		String response = customerService.disable(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
