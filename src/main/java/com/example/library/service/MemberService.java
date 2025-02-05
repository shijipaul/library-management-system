package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Member;
import com.example.library.repository.MemberRepository;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	
	public Member addMember(Member member) {
		return memberRepository.save(member);
	}
	
	
	public Member getMember(Long memberId) {
		return memberRepository.findById(memberId).orElseThrow(()->new RuntimeException("Member not found"));
	}
	
}