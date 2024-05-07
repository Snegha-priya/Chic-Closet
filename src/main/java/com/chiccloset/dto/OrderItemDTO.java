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
public class OrderItemDTO {
	    private Long id;
	    private Long productId;
	    private Long orderid;
	    private String productName;
	    private byte productimage;
	    private String productImageurl;
	    private int quantity;
	    private double unitPrice;
}
