package com.chiccloset.serviceimpl;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chiccloset.dto.AddressDTO;
import com.chiccloset.entitymodel.AddressModel;
import com.chiccloset.repository.AddressRepository;
import com.chiccloset.service.AddressService;


@Service
public class AddressSeviceImpl implements AddressService {


	@Autowired
	private AddressRepository addressRepository;

	// save
	public String save(AddressDTO addressDTO) {

		if (addressDTO != null) {

			AddressModel addressModel = AddressModel.builder().customerid(addressDTO.getCustomerId()).street(addressDTO.getStreet()).city(addressDTO.getCity()).state(addressDTO.getState()).country(addressDTO.getCountry()).zipcode(addressDTO.getZipCode()).createdtime(ZonedDateTime.now()).createdby("user").active(true).build();
			addressRepository.save(addressModel);

		} 
		return "Success";
	}}