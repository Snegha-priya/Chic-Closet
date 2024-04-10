package com.chiccloset.service;

import com.chiccloset.dto.CustomerDTO;
import com.chiccloset.dto.CustomerListDTO;

public interface CustomerService {

	String save(CustomerDTO customerDTO);

	CustomerDTO get(Long id);

	CustomerListDTO list(CustomerListDTO customerListDTO);

	String disable(Long id);

}