package com.chiccloset.dto;

import java.time.LocalDateTime;
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
public class OrderDTO {
	private Long id;
    private String customerId;
    private LocalDateTime orderDate;
    private double totalPrice;
    private String pin;
    private String itemname;
    private String paymentmode;
}
