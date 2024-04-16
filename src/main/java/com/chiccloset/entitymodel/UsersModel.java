package com.chiccloset.entitymodel;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
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
@Entity(name = "users")
@JsonIgnoreType
public class UsersModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long roleId;

	@Column(columnDefinition = "TEXT")
	private String mobileNumber;
	@Column(columnDefinition = "TEXT")
	private String password;

	@Column(columnDefinition = "TEXT")
	private String firstName;
	@Column(columnDefinition = "TEXT")
	private String lastName;
	@Column(columnDefinition = "TEXT")
	private String email;
	
	private int tenantid;
	private int failedloginattempt;
	private Boolean enabled;

	private Boolean active;
	@Column(columnDefinition = "TEXT")
	private String createdBy;
	private LocalDateTime createdTime;
	@Column(columnDefinition = "TEXT")
	private String modifiedBy;
	private LocalDateTime modifiedTime;
	
}