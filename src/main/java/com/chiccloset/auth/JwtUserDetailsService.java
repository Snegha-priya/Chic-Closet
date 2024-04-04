package com.chiccloset.auth;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chiccloset.entitymodel.RolesModel;
import com.chiccloset.entitymodel.UsersModel;
import com.chiccloset.repository.RolesRepository;
import com.chiccloset.repository.UserRolePrivilegeRepository;
import com.chiccloset.repository.UsersRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private RolesRepository userRoleRepository;

	@Autowired
	private UserRolePrivilegeRepository userRolePrivilegeRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
		UsersModel userModel = null;
		try {
			userModel = userRepository.findByMobileNumberAndActive(userName, true);
			if (userModel == null) {
				throw new UsernameNotFoundException("Invalid UserName " + userName);
			}

			RolesModel userRoleModel = userRoleRepository.findByIdAndActive(userModel.getRoleId(), true);

			List<String> privileges = userRolePrivilegeRepository.findByRoleidAndActive(userModel.getRoleId(), true);

			for (String privilege : privileges) {
				grantedAuthorities.add(new SimpleGrantedAuthority(privilege));
			}
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userRoleModel.getRole()));
			return new UserDefinedUser(userModel.getMobileNumber(), userModel.getPassword(), userModel.getEnabled(),
					true, true, true, grantedAuthorities, userRoleModel.getRole());

		} catch (Exception e) {
			logger.error("Exception in loadUserByUsername :: " + e.getMessage());
			return new User(userModel.getMobileNumber(), userModel.getPassword(), grantedAuthorities);
		}
	}
}