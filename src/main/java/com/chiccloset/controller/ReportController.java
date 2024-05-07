package com.chiccloset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiccloset.dto.AddressDTO;
import com.chiccloset.service.AddressService;
import com.chiccloset.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;

	// save
	@GetMapping("/getreport")
	public ResponseEntity<String> getReport() {
		String response = reportService.getReport();
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}

