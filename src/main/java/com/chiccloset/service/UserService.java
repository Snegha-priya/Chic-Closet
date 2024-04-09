package com.chiccloset.service;

import org.springframework.stereotype.Service;

import com.chiccloset.dto.ResponseDTO;
import com.chiccloset.dto.SignInDTO;
import com.chiccloset.dto.SignInReponseDTO;
import com.chiccloset.dto.SignUpDTO;

public interface UserService {
    String signUp(SignUpDTO signUpDTO);

    SignInReponseDTO signIn(SignInDTO signInDTO);
}
