package com.example.library.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsConfiguration {
	@Value("${spring.security.user.name}")
	private String userName;

	@Value("${spring.security.user.password}")
	private String password;

	@Value("${spring.security.user.roles}")
	private String roles;

	@Bean
	UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails admin = User.builder().username(userName).password(passwordEncoder.encode(password)).roles(roles)
				.build();

		UserDetails user = User.builder().username("user").password(passwordEncoder.encode("user123")).roles("USER")
				.build();

		return new InMemoryUserDetailsManager(admin, user);

	}

}
