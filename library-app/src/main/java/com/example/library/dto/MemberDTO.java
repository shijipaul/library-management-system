package com.example.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
	@NotBlank(message = "Name cannout be blank")
	private String name;
	@Email(message = "Please provide a well-formed email address")
	private String email;
	private String mobileNo;
	@NotBlank(message = "username cannout be blank")
	private String userName;
	@NotBlank(message = "password cannout be blank")
	private String password;
	private String role;

}
