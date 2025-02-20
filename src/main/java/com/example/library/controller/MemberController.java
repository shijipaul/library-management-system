package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Member;
import com.example.library.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/members")
@Tag(name = "Member Management",description = "APIS to manage members in Library")
public class MemberController {
	
	@Autowired
    private  MemberService memberService;

    @PostMapping
    @Operation(summary = "Add new Member", description = "create new member to the Library System")
    public ResponseEntity<?> addMember(@Valid @RequestBody Member member , BindingResult result) {
    	if (result.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<ObjectError> errors = result.getAllErrors();
            for (ObjectError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append(", ");
            }
            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(memberService.addMember(member),HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve Member Details", description = "retrieve details of member using ID")
    public Member getMember(@PathVariable Long id) {
        return memberService.getMember(id);
    }
}