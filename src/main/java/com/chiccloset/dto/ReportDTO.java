package com.chiccloset.dto;

import java.time.LocalDateTime;

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
public class ReportDTO {
	private Long id;
	private Long customerid;
	private LocalDateTime ordertime;
    private LocalDateTime endDate;
    private String orderitem;
    private double price;
}
