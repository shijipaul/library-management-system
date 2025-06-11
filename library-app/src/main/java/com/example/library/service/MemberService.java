package com.example.library.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.library.dto.MemberDto;
import com.example.library.model.Member;
import com.example.library.model.Role;
import com.example.library.repository.MemberRepository;
import com.example.library.repository.RoleRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public Member registerMember(MemberDto memberDto) {
		String roleName = (memberDto.getRole()==null || memberDto.getRole().isBlank())?"ROLE_USER":memberDto.getRole();
		Role role = roleRepository.findByRoleName(roleName)
				                  .orElseGet(()->roleRepository.save(new Role(roleName)));
		
		
		Member member=new Member();
		member.setName(memberDto.getName());
		member.setUserName(memberDto.getUserName());
		member.setEmail(memberDto.getEmail());
		member.setMobileNo(memberDto.getMobileNo());
		member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		member.setRoles(Set.of(role));
		return memberRepository.save(member);
	}
	
	
	public Member getMember(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(()->new RuntimeException("Member not found"));
	}


}