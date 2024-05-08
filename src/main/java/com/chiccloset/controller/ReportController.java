package com.chiccloset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chiccloset.dto.ReportDTO;
import com.chiccloset.service.ReportService;

@RestController
public class ReportController {

	@Autowired
	private ReportService reportService;

	// save
	@GetMapping(value = "/getreport")
	public ResponseEntity<ReportDTO> get(@RequestParam Long id) throws Exception {
		ReportDTO response = reportService.get(id);
		return new ResponseEntity<ReportDTO>(response, HttpStatus.OK);
	}
}

