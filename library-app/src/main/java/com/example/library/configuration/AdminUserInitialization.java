package com.example.library.configuration;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.library.model.Member;
import com.example.library.model.Role;
import com.example.library.repository.MemberRepository;
import com.example.library.repository.RoleRepository;

@Component
public class AdminUserInitialization implements CommandLineRunner{
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		if(roleRepository.count()==0) {
			Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN").orElseGet(()->roleRepository.save(new Role("ROLE_ADMIN")));
			Member admin = new Member();
            admin.setName("Admin User");
            admin.setEmail("admin@example.com");
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // Secure password
            admin.setRoles(Set.of(adminRole));
			memberRepository.save(admin);
		}
		
	}

}
