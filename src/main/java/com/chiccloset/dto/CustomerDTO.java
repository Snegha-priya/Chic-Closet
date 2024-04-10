package com.chiccloset.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_NULL)
public class CustomerDTO {
	private Long id;
	private String fullName;
	private String email;
	private String phoneNumber;
	private Long addressId;
	private boolean active;
	private List<CustomerAddressDTO> customerAddress;
}