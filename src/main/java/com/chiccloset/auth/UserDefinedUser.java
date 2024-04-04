package com.chiccloset.auth;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDefinedUser extends User {

	private static final long serialVersionUID = 1L;

	public UserDefinedUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			String role) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

		this.setRole(role);
	}

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
