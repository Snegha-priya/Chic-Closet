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
public class ProductDTO {
	private Long id;
	private long categoryId;
	private String name;
	private String description;
	private long price;
	private long quantity;
	private byte[] image;
	private String imageName;
	private String imageUrl;

}
