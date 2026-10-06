package com.example.library.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BorrowRequestDTO {
	
	private MemberRequestDTO member;
	private BookRequestDTO book;
	private Date borrowDate;
	private Date returnDate;
	private double fine;
}
