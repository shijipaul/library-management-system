package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Borrow;
import com.example.library.service.BorrowService;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

	@Autowired
    private BorrowService borrowService;
	
	@PostMapping("/borrow")
    public Borrow borrowBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        return borrowService.borrowbook(bookId, memberId);
    }
	
	@PostMapping("/return")
    public Borrow returnBook(@RequestParam Long borrowId) {
        return borrowService.returnBook(borrowId);
    }

}
