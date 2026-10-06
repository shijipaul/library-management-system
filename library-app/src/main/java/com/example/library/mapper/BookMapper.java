package com.example.library.mapper;

import org.springframework.stereotype.Component;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.model.Book;

@Component
public class BookMapper {
	public Book mapToBookEntity(BookRequestDTO bookRequest) {
		Book book = new Book();
		book.setAuthor(bookRequest.getAuthor());
		book.setAvailableCopies(bookRequest.getAvailableCopies());
		book.setTitle(bookRequest.getTitle());
		return book;
	}
	
	public BookResponseDTO mapToBookDTO(Book book) {
		BookResponseDTO bookResponse = new BookResponseDTO();
		bookResponse.setId(book.getId());
		bookResponse.setAuthor(book.getAuthor());
		bookResponse.setAvailableCopies(book.getAvailableCopies());
		bookResponse.setTitle(book.getTitle());
		return bookResponse;
	}

	public BookRequestDTO mapBookResponseDTOToBookRequestDTO(BookResponseDTO bookResponse) {
		BookRequestDTO bookRequest = new BookRequestDTO();
		bookRequest.setAuthor(bookResponse.getAuthor());
		bookRequest.setAvailableCopies(bookResponse.getAvailableCopies());
		bookRequest.setTitle(bookResponse.getTitle());
		return null;
	}

}
