package com.example.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	/* Securing the requests from unauthorized Access */
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		http.csrf(csrf->csrf.disable())
		    .authorizeHttpRequests(auth->auth.requestMatchers("/swagger-ui**/**","/v3/api-docs/**").permitAll()
		    		.requestMatchers("/api/borrrows/**").hasRole("ADMIN")
		    		.requestMatchers("/api/books/**","api/members/**").hasAnyRole("USER","ADMIN")
		    		.anyRequest().authenticated())
		    .httpBasic(basic->{});
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
