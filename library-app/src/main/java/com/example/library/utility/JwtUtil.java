package com.example.library.utility;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/*Handles Token Creation and validation*/
@Component
public class JwtUtil {
	private final long expiration = 3600000;
	private final String secret = "GYCKw4zR181X3O0ve84fwrXjyTaxiaPOEdgDFtXBFQc=";

	public String generateJwtTocken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes()), SignatureAlgorithm.HS256).compact();
	}

	public boolean validateTocken(String token, UserDetails userDetails) {
		return extractUserName(token).equals(userDetails.getUsername()) && !isTockenExpired(token);
	}

	private boolean isTockenExpired(String token) {
		Date expiration = Jwts.parserBuilder()
				.setSigningKey(secret.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
		return expiration.before(new Date());
	}

	public String extractUserName(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(secret.getBytes())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();

	}

}
