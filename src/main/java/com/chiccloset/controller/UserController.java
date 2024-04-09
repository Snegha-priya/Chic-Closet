package com.chiccloset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chiccloset.dto.ResponseDTO;
import com.chiccloset.dto.SignInDTO;
import com.chiccloset.dto.SignInReponseDTO;
import com.chiccloset.dto.SignUpDTO;
import com.chiccloset.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;

	// signup
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignUpDTO signupDto) {
		String response = userService.signUp(signupDto);
		return new ResponseEntity<String>(response, HttpStatus.OK);

	}

	// signin
	@PostMapping("/signin")
	public ResponseEntity<String> signin(@RequestBody SignInDTO signinDto) {
		SignInReponseDTO response = userService.signIn(signinDto);
		return new ResponseEntity<String>(HttpStatus.OK);

	}

}
