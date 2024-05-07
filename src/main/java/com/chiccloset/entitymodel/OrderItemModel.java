package com.chiccloset.entitymodel;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "orderitem")
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderid;
    @ManyToOne
    private OrderModel order;
    private Long productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private Boolean active;
    private LocalDateTime createdtime;
    private String Createdby;
}