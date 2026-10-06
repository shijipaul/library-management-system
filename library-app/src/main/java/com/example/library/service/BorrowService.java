package com.example.library.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.dto.BorrowRequestDTO;
import com.example.library.dto.BorrowResponseDTO;
import com.example.library.dto.MemberRequestDTO;
import com.example.library.dto.MemberResponseDTO;
import com.example.library.mapper.BookMapper;
import com.example.library.mapper.BorrowMapper;
import com.example.library.mapper.MemberMapper;
import com.example.library.model.Borrow;
import com.example.library.repository.BorrowRepository;

@Service
public class BorrowService {
    private static final Logger logger = LoggerFactory.getLogger(BorrowService.class);
    

	private final BorrowRepository borrowRepository;
    private final BookService bookService;
    private final MemberService memberService ;
    private final BorrowMapper borrowMapper;
    private final MemberMapper memberMapper;
    private final BookMapper bookMapper;
    
    public BorrowService(BorrowRepository borrowRepository,BookService bookService,MemberService memberService,BorrowMapper borrowMapper,MemberMapper memberMapper,BookMapper bookMapper) {
    	this.borrowRepository = borrowRepository;
    	this.bookService = bookService;
    	this.memberService = memberService;
    	this.borrowMapper = borrowMapper;
    	this.memberMapper = memberMapper;
    	this.bookMapper = bookMapper;
    	
    }
	  
	
	
	public Borrow borrowbook(Long bookId , Long memberId) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		MemberResponseDTO memberResponse = memberService.getMember(memberId);
		MemberRequestDTO memberRequest = memberMapper.mapMemberResponseDTOToMemberRequestDTO(memberResponse);
		BookResponseDTO bookResponse = bookService.getBook(bookId);
		
		if(bookResponse.getAvailableCopies() <= 0) {
			throw new RuntimeException("no  available copies for this book!");
		}
		bookResponse.setAvailableCopies(bookResponse.getAvailableCopies()-1);
		BookRequestDTO bookRquest = bookMapper.mapBookResponseDTOToBookRequestDTO(bookResponse);

		BorrowRequestDTO borrowRequest = new BorrowRequestDTO();
		borrowRequest.setBook(bookRquest);
		borrowRequest.setMember(memberRequest);
		borrowRequest.setBorrowDate(new Date());
		
		
		bookService.updateBook(bookRquest,bookId);
		Borrow borrow = borrowMapper.mapToBorrowEntity(borrowRequest);
		Thread.sleep(2000);
		long endTime = System.currentTimeMillis();
		logger.info("Execution Time : {}ms"+(endTime - startTime));
		return borrowRepository.save(borrow);
	}
	
	public Borrow returnBook(Long borrowId) {
		
		
		Borrow borrow = borrowRepository.findById(borrowId).orElseThrow(()->new RuntimeException("Borrow record not found!"));
		BorrowResponseDTO borrowResponse = borrowMapper.mapToBorrowDTO(borrow);
		borrowResponse.setReturnDate(new Date());
		
		//fine calculation logic
		Long diffInMillies = Math.abs(borrow.getReturnDate().getTime()-borrow.getBorrowDate().getTime());
		
		Long diffInDays = diffInMillies /(1000*60*60*24);
		
		if(diffInDays > 14) {
			borrowResponse.setFine((diffInDays - 14)*1.0);
		}
		
		BookResponseDTO bookResponse = borrowResponse.getBook();
		bookResponse.setAvailableCopies(bookResponse.getAvailableCopies() + 1);
		
		BookRequestDTO bookRequest = bookMapper.mapBookResponseDTOToBookRequestDTO(bookResponse);
		bookService.updateBook(bookRequest, bookResponse.getId());
		
		
		return borrowRepository.save(borrow);
		
	}
	
	
}
