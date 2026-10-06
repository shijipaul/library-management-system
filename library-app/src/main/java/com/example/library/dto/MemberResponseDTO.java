package com.example.library.dto;

import java.util.Set;

import com.example.library.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDTO {
	private Long id;
	private String name;
	private String email;
	private String mobileNo;
	private String userName;
	private String password;
	private Set<Role> roles;
}
