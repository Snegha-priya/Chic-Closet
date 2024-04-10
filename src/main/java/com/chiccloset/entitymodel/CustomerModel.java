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
@Entity(name = "customer")
public class CustomerModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String fullName;
	public String email;
	public String phoneNumber;
	public Long addressId;
	private boolean active;	
	private String createdby;	
	private LocalDateTime createdtime;
	private String modifiedby;
	private LocalDateTime modifiedtime;
}
