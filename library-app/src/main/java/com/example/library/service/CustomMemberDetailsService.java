package com.example.library.service;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.library.model.Member;
import com.example.library.repository.MemberRepository;

@Service
public class CustomMemberDetailsService implements UserDetailsService{
	private static final Logger logger = LoggerFactory.getLogger(CustomMemberDetailsService.class);
    @Autowired	 
	private MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("User Name:"+username);
		Member member = memberRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Member Not Found :" + username));
		
		
		return new User(member.getUserName(), 
				member.getPassword(),
				member.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toList())
				);
	}

}
