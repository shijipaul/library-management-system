package com.example.library.service;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {
	
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	
	public Book addBook(Book book) {
		logger.info("Adding new Book : "+book.getTitle());
		return bookRepository.save(book);
	}
	
	
	public void deleteBook(Long bookId) {
		 logger.info("Deleting  Book with Id : "+bookId);
		 bookRepository.deleteById(bookId);
	}
	
	
	public  Book getBook(Long bookId) {
		logger.info("Retrieving details of  Book with ID : "+bookId);
		return bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book not found"));
	}
	
	
	public Book updateBook(Book book , Long bookId) {
		logger.info("Updating  Book with ID : "+bookId);
		Book existingBook = bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("Book is not found"));
		existingBook.setAuthor(book.getAuthor());
		existingBook.setTitle(book.getTitle());
		existingBook.setAvailableCopies(book.getAvailableCopies());
		return bookRepository.save(existingBook);
	}
	
}