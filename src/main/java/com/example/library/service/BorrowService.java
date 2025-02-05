package com.example.library.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.entity.Borrow;
import com.example.library.entity.Member;
import com.example.library.repository.BorrowRepository;

@Service
public class BorrowService {

	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MemberService memberService ;
	
	
	
	public Borrow borrowbook(Long bookId , Long memberId) {
		
		Member member = memberService.getMember(memberId);
		Book book = bookService.getBook(bookId);
		
		if(book.getAvailableCopies() <= 0) {
			throw new RuntimeException("no  available copies for this book!");
		}
		
		Borrow borrow = new Borrow();
		borrow.setBook(book);
		borrow.setMember(member);
		borrow.setBorrowDate(new Date());
		
		book.setAvailableCopies(book.getAvailableCopies()-1);
		bookService.updateBook(book,bookId);
		
		return borrowRepository.save(borrow);
	}
	
	public Borrow returnBook(Long borrowId) {
		
		Borrow borrow = borrowRepository.findById(borrowId).orElseThrow(()->new RuntimeException("Borrow record not found!"));
		borrow.setReturnDate(new Date());
		
		//fine calculation logic
		Long diffInMillies = Math.abs(borrow.getReturnDate().getTime()-borrow.getBorrowDate().getTime());
		
		Long diffInDays = diffInMillies /(1000*60*60*24);
		
		if(diffInDays > 14) {
			borrow.setFine((diffInDays - 14)*1.0);
		}
		
		Book book = borrow.getBook();
		book.setAvailableCopies(book.getAvailableCopies() + 1);
		bookService.updateBook(book, book.getId());
		
		
		return borrowRepository.save(borrow);
		
	}
	
	
}
