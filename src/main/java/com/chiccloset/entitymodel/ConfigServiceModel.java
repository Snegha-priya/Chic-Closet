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
@Entity(name = "configService")
public class ConfigServiceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String servicename;
	private String servicemethod;
	private String serviceurl;
	private boolean active;
	private String createdby;
	private LocalDateTime createdtime;
	private String modifiedby;
	private LocalDateTime modifiedtime;
}
