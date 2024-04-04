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

import com.chiccloset.dto.ContactUsDTO;
import com.chiccloset.service.ContactUsService;

@RestController
public class ContactUsController {

	@Autowired
	private ContactUsService contactUsService;

	// save
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody List<ContactUsDTO> contactUsDTOList) {
		String response = contactUsService.save(contactUsDTOList);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// delete
	@DeleteMapping(value = "/remove")
	public ResponseEntity<String> remove(@RequestParam Long id) {
		String response = contactUsService.remove(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// list

	@GetMapping("/list")
	public ResponseEntity<List<ContactUsDTO>> list() {
		List<ContactUsDTO> contactUsDTOList = contactUsService.list();
		return ResponseEntity.ok(contactUsDTOList);
	}

}