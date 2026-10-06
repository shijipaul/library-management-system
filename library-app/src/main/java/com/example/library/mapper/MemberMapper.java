package com.example.library.mapper;

import org.springframework.stereotype.Component;

import com.example.library.dto.MemberRequestDTO;
import com.example.library.dto.MemberResponseDTO;
import com.example.library.model.Member;

@Component
public class MemberMapper {
	
public Member mapToMemberEntity(MemberRequestDTO memberRequest) {
	
	Member member = new Member();
	member.setEmail(memberRequest.getEmail());
	member.setMobileNo(memberRequest.getMobileNo());
	member.setName(memberRequest.getName());
	member.setPassword(memberRequest.getPassword());
	member.setRoles(memberRequest.getRoles());
	member.setUserName(memberRequest.getUserName());
	return member;
	
}

public  MemberResponseDTO mapToMemberDTO(Member member) {
	MemberResponseDTO MemberResponseDTO = new MemberResponseDTO();
	MemberResponseDTO.setEmail(member.getEmail());
	MemberResponseDTO.setMobileNo(member.getMobileNo());
	MemberResponseDTO.setName(member.getName());
	MemberResponseDTO.setPassword(member.getPassword());
	MemberResponseDTO.setRoles(member.getRoles());
	MemberResponseDTO.setUserName(null);
	return null;
	
}

public MemberRequestDTO mapMemberResponseDTOToMemberRequestDTO(MemberResponseDTO memberResponse) {
	MemberRequestDTO memberRequest = new MemberRequestDTO();
	memberRequest.setEmail(memberResponse.getEmail());
	memberRequest.setMobileNo(memberResponse.getMobileNo());
	memberRequest.setName(memberResponse.getName());
	memberRequest.setPassword(memberResponse.getPassword());
	memberRequest.setRoles(memberResponse.getRoles());
	memberRequest.setUserName(memberResponse.getUserName());
	return memberRequest;
}
}
