package com.chiccloset.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerListDTO {
	private int pageNumber;
	private int listSize;
	private long count;
	private long totalPages;
	private String searchString;
	private List<CustomerDTO> customers;
}
