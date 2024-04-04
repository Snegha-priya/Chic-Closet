package com.chiccloset.service;

import java.util.List;

import com.chiccloset.dto.ContactUsDTO;

public interface ContactUsService {

	String remove(Long id);

	String save(List<ContactUsDTO> contactUsDTOList);

	List<ContactUsDTO> list();
	

}