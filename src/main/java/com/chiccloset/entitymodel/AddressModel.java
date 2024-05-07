package com.chiccloset.entitymodel;

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
@Entity(name = "address")
public class AddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String customerid;
	private String street;
	private String country;
	private String city;
	private String state;
	private String zipcode;
	private ZonedDateTime createdtime;
	private String createdby;
	private String modifiedby;
	private ZonedDateTime modifiedtime;
	private boolean active;
}
