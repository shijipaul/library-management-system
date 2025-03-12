package com.example.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.Borrow;
import com.example.library.service.BorrowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/borrows")
@Tag(name = "Borrow Service", description = "APIs to manage Borrow Service in Library Mangament System")
public class BorrowController {

	@Autowired
    private BorrowService borrowService;
	
	@PostMapping("/borrow")
	@Operation(summary = "Borrow Book", description = "Member borrowed a Book")
    public Borrow borrowBook(@RequestParam Long bookId, @RequestParam Long memberId) {
        return borrowService.borrowbook(bookId, memberId);
    }
	
	@PostMapping("/return")
	 @Operation(summary = "Returning borrowed Book", description = "Returning Borrowed Book by the Member")
    public Borrow returnBook(@RequestParam Long borrowId) {
        return borrowService.returnBook(borrowId);
    }

}
