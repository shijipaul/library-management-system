package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}
	/*
	 * I've implemented so far:
	 * 
	 * ✅ Spring Boot monolith with Book, Member, and Borrow services
	 * 
	 * ✅ Global Exception Handling, Validation, Logging
	 * 
	 * ✅ Swagger/OpenAPI
	 * 
	 * ✅ Spring Security with JWT and DB-backed user authentication + Role entity
	 * 
	 * ✅ Dockerized (multi-stage build)
	 * 
	 * ✅ Docker Compose (Spring Boot + MySQL + Healthcheck + .env for secrets)
	 */


}
