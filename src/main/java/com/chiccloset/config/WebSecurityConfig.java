package com.chiccloset.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chiccloset.auth.JwtRequestFilter;
import com.chiccloset.auth.JwtUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/**").permitAll()
				.requestMatchers(HttpMethod.OPTIONS, "**").permitAll().anyRequest().authenticated());
		http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}