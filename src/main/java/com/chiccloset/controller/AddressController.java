package com.chiccloset.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chiccloset.dto.AddressDTO;
import com.chiccloset.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	private AddressService addressService;

	// save
	@PostMapping("/addresssave")
	public ResponseEntity<String> save(@RequestBody AddressDTO addressDTO) {
		String response = addressService.save(addressDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
