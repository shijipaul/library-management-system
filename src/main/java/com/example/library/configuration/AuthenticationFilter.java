package com.example.library.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.library.utility.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	
	/* Intercept requests and set authentication in the security context. */
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userdetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer")) {
			String jwt = authHeader.substring(7);
			String username = jwtUtil.extractUserName(jwt);
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = userdetailsService.loadUserByUsername(username);
				if (jwtUtil.validateTocken(jwt,userDetails)) { /* checks tocken validity(signature,expiration,username) */
					UsernamePasswordAuthenticationToken authTocken = new UsernamePasswordAuthenticationToken( /*wraps user identity and roles */
							userDetails, null, userDetails.getAuthorities());
					authTocken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));/* Adds IP/session info (optional) */
					SecurityContextHolder.getContext().setAuthentication(authTocken);/* Marks user as logged in for the request 
					                                                                   This tells Spring Security:" The user from this request is now authenticated.”*/
				}
			}

		}
		filterChain.doFilter(request, response); /* Continues request processing */
	}

}
