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

import com.chiccloset.dto.CategoryDTO;
import com.chiccloset.dto.ContactUsDTO;
import com.chiccloset.service.CategoryService;
import com.chiccloset.service.ContactUsService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// save
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody List<CategoryDTO> categoryDTOList) {
		String response = categoryService.save(categoryDTOList);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
