package com.chiccloset.entitymodel;

import java.time.LocalDateTime;

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
@Entity(name = "customeraddress")
public class CustomerAddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long customerId;
	private String state;
	private String city;
	private String country;
	private String receiverName;
	private boolean active;
	private String createdby;
	private LocalDateTime createdtime;
	private String modifiedby;
	private LocalDateTime modifiedtime;

}
