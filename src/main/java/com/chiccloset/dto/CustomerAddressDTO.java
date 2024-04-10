package com.chiccloset.dto;

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
public class CustomerAddressDTO {
	private Long id;
	private Long customerId;
	private String city;
	private String state;
	private String addressType;
	private String phoneNumber;
	private String receiverName;
	private String country;
}