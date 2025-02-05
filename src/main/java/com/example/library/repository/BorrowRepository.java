package com.example.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.library.entity.Borrow;
@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Long>{

}
