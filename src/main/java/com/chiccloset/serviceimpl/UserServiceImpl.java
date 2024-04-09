package com.chiccloset.serviceimpl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiccloset.dto.SignInDTO;
import com.chiccloset.dto.SignInReponseDTO;
import com.chiccloset.dto.SignUpDTO;
import com.chiccloset.entitymodel.AuthenticationToken;
import com.chiccloset.entitymodel.UsersModel;
import com.chiccloset.exception.AuthenticationFailException;
import com.chiccloset.exception.CustomException;
import com.chiccloset.repository.UsersRepository;
import com.chiccloset.service.AuthenticationService;
import com.chiccloset.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UsersRepository userRepository;

	@Autowired
	AuthenticationService authenticationService;

	@Transactional
	public String signUp(SignUpDTO signupDto) {
		// check if user is already present
		if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
			// we have an user
			throw new CustomException("user already present");
		}

		// hash the password

		String encryptedpassword = signupDto.getPassword();

		try {
			encryptedpassword = hashPassword(signupDto.getPassword());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		UsersModel user = UsersModel.builder().firstName(signupDto.getFirstName()).lastName(signupDto.getLastName())
				.email(signupDto.getEmail()).password(encryptedpassword).build();
		userRepository.save(user);

		// save the user

		// create the token

		final AuthenticationToken authenticationToken = new AuthenticationToken(null, encryptedpassword, null, user);

		authenticationService.saveConfirmationToken(authenticationToken);

		String responseDto = "user created succesfully";
		return responseDto;
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	public SignInReponseDTO signIn(SignInDTO signInDto) {
		// find user by email

		UsersModel user = userRepository.findByEmail(signInDto.getEmail());

		if (Objects.isNull(user)) {
			throw new AuthenticationFailException("user is not valid");
		}

		// hash the password

		try {
			if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
				throw new AuthenticationFailException("wrong password");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// compare the password in DB

		// if password match

		AuthenticationToken token = authenticationService.getToken(user);

		// retrive the token

		if (Objects.isNull(token)) {
			throw new CustomException("token is not present");
		}

		return new SignInReponseDTO("sucess", token.getToken());

		// return response
	}
}
