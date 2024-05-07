package com.chiccloset.entitymodel;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "orders")
public class OrderModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerid;
    private LocalDateTime orderdate;
    private String itemname;
    private String pin;
    private double totalprice;
    private String paymentmode;
    private Boolean active;
    private LocalDateTime createdtime;
    private String createdby;
}