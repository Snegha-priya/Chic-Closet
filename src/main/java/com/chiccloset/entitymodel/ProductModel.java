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
@Entity(name = "product")
public class ProductModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long categoryID;
	private String name;
	private String description;
	private String imageName;
	private Long price;
	private Long quantity;
	private Boolean active;
	private String createdBy;
	private ZonedDateTime createdTime;
	private String modifiedBy;
	private ZonedDateTime modifiedTime;
}