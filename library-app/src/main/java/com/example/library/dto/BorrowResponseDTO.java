package com.example.library.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowResponseDTO {
	private Long id;
	private BookResponseDTO book;
	private MemberResponseDTO member;
	private Date borrowDate;
	private Date returnDate;
	private double fine;

}
