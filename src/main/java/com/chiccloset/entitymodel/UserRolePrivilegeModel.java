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
@Entity(name = "userRolePrivilege")
public class UserRolePrivilegeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;
	private long roleId;
	private long privilegeId;
	
	private boolean active;
	@Column(columnDefinition = "TEXT")
	private String createdBy;
	private LocalDateTime createdTime;
	@Column(columnDefinition = "TEXT")
	private String modifiedBy;
	private LocalDateTime modifiedTime;

}
