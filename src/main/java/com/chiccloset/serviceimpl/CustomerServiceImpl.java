package com.chiccloset.serviceimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.chiccloset.dto.CustomerAddressDTO;
import com.chiccloset.dto.CustomerDTO;
import com.chiccloset.dto.CustomerListDTO;
import com.chiccloset.entitymodel.CustomerAddressModel;
import com.chiccloset.entitymodel.CustomerModel;
import com.chiccloset.exception.EcommerceException;
import com.chiccloset.repository.CustomerAddressRepository;
import com.chiccloset.repository.CustomerRepository;
import com.chiccloset.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerAddressRepository customerAddressRepository;

	// save
	public String save(CustomerDTO customerDTO) {

		CustomerModel customerModel = CustomerModel.builder().id(customerDTO.getId())
				.fullName(customerDTO.getFullName()).email(customerDTO.getEmail())
				.phoneNumber(customerDTO.getPhoneNumber()).addressId(customerDTO.getAddressId()).active(true)
				.createdby(null).createdtime(LocalDateTime.now()).modifiedby(null).modifiedtime(LocalDateTime.now())
				.build();
		customerModel = customerRepository.save(customerModel);

		Long cusid = customerModel.getId();
		List<CustomerAddressDTO> customerAddressDTOList = customerDTO.getCustomerAddress();
		List<CustomerAddressModel> customerAddressModelList = new ArrayList<>();
		if (customerAddressDTOList != null && !customerAddressDTOList.isEmpty()) {

			for (CustomerAddressDTO customerAddressDTO : customerAddressDTOList) {
				CustomerAddressModel customerAddressModel = CustomerAddressModel.builder()
						.id(customerAddressDTO.getId()).customerId(cusid).state(customerAddressDTO.getState())
						.city(customerAddressDTO.getCity()).country(customerAddressDTO.getCountry())
						.receiverName(customerAddressDTO.getReceiverName()).active(true).build();
				customerAddressModelList.add(customerAddressModel);
			}
			customerAddressRepository.saveAll(customerAddressModelList);

		}

		return "Success";
	}

	// get

	public CustomerDTO get(Long id) {
		CustomerDTO customerDTO = new CustomerDTO();

		if (id == 0) {
			throw new EcommerceException("1010");
		}

		CustomerModel customerModel = customerRepository.findByIdAndActive(id, true);
		logger.info("customermodel" + customerModel);

		customerDTO = constructResponse(customerModel);

		return customerDTO;
	}

	private CustomerDTO constructResponse(CustomerModel customerModel) {

		CustomerDTO customerDTO = CustomerDTO.builder().id(customerModel.getId()).fullName(customerModel.getFullName())
				.email(customerModel.getEmail()).phoneNumber(customerModel.getPhoneNumber())
				.addressId(customerModel.getAddressId()).active(true).build();

		List<CustomerAddressDTO> customerAddressDTOList = new ArrayList<>();

		Long cusid = customerModel.getId();
		List<CustomerAddressModel> customerAddressModelList = customerAddressRepository.findByCustomerIdAndActive(cusid,
				true);
		if (customerAddressModelList != null && customerAddressModelList.size() > 0) {
			for (CustomerAddressModel customerAddressModel : customerAddressModelList) {

				CustomerAddressDTO customerAddressDTO = CustomerAddressDTO.builder().id(customerAddressModel.getId())
						.customerId(customerAddressModel.getCustomerId()).state(customerAddressModel.getState())
						.city(customerAddressModel.getCity()).country(customerAddressModel.getCountry())
						.receiverName(customerAddressModel.getReceiverName()).build();
				customerAddressDTOList.add(customerAddressDTO);
			}
		}
		customerDTO.setCustomerAddress(customerAddressDTOList);
		return customerDTO;
	}

	// list

	public CustomerListDTO list(CustomerListDTO customerListDTO) {
		Pageable paging = null;
		Page<CustomerModel> PagecustomerModel = null;
		if (customerListDTO.getPageNumber() > 0 && customerListDTO.getListSize() > 0) {
			paging = PageRequest.of(customerListDTO.getPageNumber() - 1, customerListDTO.getListSize(),
					Sort.by("createdtime").descending());
		} else {
			paging = PageRequest.of(0, 25, Sort.by("createdtime").descending());
		}

		if (customerListDTO.getSearchString() != null && !customerListDTO.getSearchString().isEmpty()) {
			PagecustomerModel = customerRepository.findByFullNameContainingIgnoreCaseOrPhoneNumberContainsAndActive(
					customerListDTO.getSearchString(), customerListDTO.getSearchString(), true, paging);
			logger.debug("findByFullNameContainingIgnoreCaseAndPhoneNumberContainsAndActive");
		} else {
			PagecustomerModel = customerRepository.findByActive(true, paging);
			logger.debug("findByActive");
		}

		List<CustomerDTO> customerDTO = PagecustomerModel.stream().map(model -> constructResponse(model))
				.collect(Collectors.toList());
		logger.info("customerDTO :: " + customerDTO);
		customerListDTO.setCustomers(customerDTO);
		customerListDTO.setCount(PagecustomerModel.getTotalElements());
		customerListDTO.setTotalPages(PagecustomerModel.getTotalPages());
		return customerListDTO;

	}

	// disable
	public String disable(Long id) {

		CustomerModel customerModel = customerRepository.findByIdAndActive(id, true);
		List<CustomerAddressModel> customerAddressModelList = customerAddressRepository.findByActiveAndId(true,
				customerModel.getId());

		if (customerModel != null) {
			customerModel.setActive(false);
		}
		customerRepository.save(customerModel);

		if (customerAddressModelList != null) {
			for (CustomerAddressModel customerAddressModel : customerAddressModelList) {
				customerAddressModel.setActive(false);
				customerAddressRepository.save(customerAddressModel);
			}
		}

		return "Success";
	}

}