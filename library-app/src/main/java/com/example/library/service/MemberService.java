package com.example.library.service;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.library.dto.MemberDTO;
import com.example.library.dto.MemberResponseDTO;
import com.example.library.mapper.MemberMapper;
import com.example.library.model.Member;
import com.example.library.model.Role;
import com.example.library.repository.MemberRepository;
import com.example.library.repository.RoleRepository;

@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final MemberMapper memberMapper;

	public MemberService(MemberRepository memberRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder,MemberMapper memberMapper) {
		this.memberRepository = memberRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.memberMapper = memberMapper;
	}

	public Member registerMember(MemberDTO memberDto) {
		String roleName = (memberDto.getRole() == null || memberDto.getRole().isBlank()) ? "ROLE_USER"
				: memberDto.getRole();
		Role role = roleRepository.findByRoleName(roleName).orElseGet(() -> roleRepository.save(new Role(roleName)));

		Member member = new Member();
		member.setName(memberDto.getName());
		member.setUserName(memberDto.getUserName());
		member.setEmail(memberDto.getEmail());
		member.setMobileNo(memberDto.getMobileNo());
		member.setPassword(passwordEncoder.encode(memberDto.getPassword()));
		member.setRoles(Set.of(role));
		return memberRepository.save(member);
	}

	public MemberResponseDTO getMember(Long memberId) {
		
		 Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
		 MemberResponseDTO memberResponse = memberMapper.mapToMemberDTO(member);
		return memberResponse;
	}

}