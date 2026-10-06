package com.example.library.mapper;

import org.springframework.stereotype.Component;

import com.example.library.dto.BorrowRequestDTO;
import com.example.library.dto.BorrowResponseDTO;
import com.example.library.model.Borrow;

@Component
public class BorrowMapper {
	private final BookMapper bookmapper;
	private final MemberMapper membeMapper;

	public BorrowMapper(BookMapper bookmapper,MemberMapper membeMapper) {
		this.bookmapper=bookmapper;
		this.membeMapper = membeMapper;
	}
	public BorrowResponseDTO mapToBorrowDTO(Borrow borrow) {
		BorrowResponseDTO borrowResponse = new BorrowResponseDTO();
		borrowResponse.setId(borrow.getId());
		borrowResponse.setBook(bookmapper.mapToBookDTO(borrow.getBook()));
		borrowResponse.setBorrowDate(borrow.getBorrowDate());
		borrowResponse.setFine(borrow.getFine());
		borrowResponse.setId(borrow.getId());
		borrowResponse.setReturnDate(borrow.getReturnDate());
		borrowResponse.setMember(membeMapper.mapToMemberDTO(borrow.getMember()));
		return borrowResponse;

	}

	public Borrow mapToBorrowEntity(BorrowRequestDTO borrowRequest) {
		Borrow borrow = new Borrow();
		borrow.setBook(bookmapper.mapToBookEntity(borrowRequest.getBook()));
		borrow.setBorrowDate(borrowRequest.getBorrowDate());
		borrow.setFine(borrowRequest.getFine());
		borrow.setMember(membeMapper.mapToMemberEntity(borrowRequest.getMember()));
		borrow.setReturnDate(borrowRequest.getReturnDate());
		return borrow;

	}
}
