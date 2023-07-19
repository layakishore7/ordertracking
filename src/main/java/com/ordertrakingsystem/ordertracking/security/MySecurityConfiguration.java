package com.ordertrakingsystem.ordertracking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class MySecurityConfiguration {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests().anyRequest().authenticated();

		http.httpBasic();
		http.formLogin();

		http.csrf().disable();

		return http.build();
	}
}
