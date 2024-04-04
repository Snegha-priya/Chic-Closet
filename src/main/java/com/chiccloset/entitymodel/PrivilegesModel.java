package com.chiccloset.entitymodel;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
@Entity(name = "privileges")
public class PrivilegesModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(columnDefinition = "TEXT")
	private String privilege;
	
	private boolean active;
	@Column(columnDefinition = "TEXT")
	private String createdBy;
	private LocalDateTime createdTime;
	@Column(columnDefinition = "TEXT")
	private String modifiedBy;
	private LocalDateTime modifiedTime;

}
