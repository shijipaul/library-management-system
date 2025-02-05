package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}
	
	
	public void deleteBook(Long bookId) {
		 bookRepository.deleteById(bookId);//do I need to check if that book exist?
	}
	
	
	public  Book getBook(Long bookId) {
		return bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
	}
	
	
	public Book updateBook(Book book , Long bookId) {
		Book existingBook = bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book is not found"));
		existingBook.setAuthor(book.getAuthor());
		existingBook.setTitle(book.getTitle());
		existingBook.setAvailableCopies(book.getAvailableCopies());
		return bookRepository.save(existingBook);
	}
	
}