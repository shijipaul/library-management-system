package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Member;
import com.example.library.service.MemberService;

@RestController
@RequestMapping("/members")
public class MemberController {
	@Autowired
    private  MemberService memberService;

    @PostMapping("/add")
    public Member addMember(@RequestBody Member member) {
        return memberService.addMember(member);
    }
}