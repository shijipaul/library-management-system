package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { }