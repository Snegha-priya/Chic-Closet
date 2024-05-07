package com.chiccloset.entitymodel;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "report")
public class ReportModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customerid;
	private LocalDateTime ordertime;
    private LocalDateTime endDate;
    private String orderitem;
    private double price;
    private boolean active;
    private LocalDateTime createdtime;
    private String CreatedBy;
}