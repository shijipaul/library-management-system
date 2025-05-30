package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.dto.AuthRequest;
import com.example.library.dto.AuthResponse;
import com.example.library.utility.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth/")
@Tag(name = "Authentication", description = "APIs for authentication purpose")
public class AuthController {
	
	/* Endpoints for users to authenticate and recieve JWT Tocken */
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authmanager;

	@PostMapping("/login")
	@Operation(summary = "Jwt tocken generation", description = "To generate jwt tocken for authorized login")
	public ResponseEntity<?> generateTocken(@RequestBody AuthRequest authRequest) {

		authmanager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		String jwt = jwtUtil.generateJwtTocken(userDetails);
		return ResponseEntity.ok(new AuthResponse(jwt));

	}

}
