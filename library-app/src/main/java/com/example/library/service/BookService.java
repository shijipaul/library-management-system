package com.example.library.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.exception.BookNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.model.Book;
import com.example.library.model.BookAuditLog;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

	private static final Logger logger = LoggerFactory.getLogger(BookService.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookAuditLogKafkaProducer auditLogProducer;
	
	private BookMapper bookMapper;

	public BookResponseDTO addBook(BookRequestDTO bookRequest) {
		logger.info("Adding new Book : " + bookRequest.getTitle());
		Book book = bookMapper.mapToBookEntity(bookRequest);
		Book savedBook = bookRepository.save(book);
		logBookAction(savedBook.getId(), "CREATED");
		BookResponseDTO bookResponse = bookMapper.mapToBookDTO(savedBook);
		return bookResponse;
	}

	@CacheEvict(key = "#bookId", value = "books")
	public void deleteBook(Long bookId) {
		logger.info("Deleting  Book with Id : " + bookId);
		bookRepository.deleteById(bookId);
		logBookAction(bookId, "DELETED");
	}

	@Cacheable(key = "#bookId", value = "books")
	public Book getBook(Long bookId) {
		logger.info("Retrieving the details of  Book with ID : " + bookId);
		return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));

	}

	@CachePut(key = "#bookId", value = "books")
	public BookResponseDTO updateBook(BookRequestDTO bookRequest, Long bookId) {
		logger.info("Updating  Book with ID : " + bookId);
		Book existingBook = bookRepository.findById(bookId)
				.orElseThrow(() -> new RuntimeException("Book is not found"));
		existingBook.setAuthor(bookRequest.getAuthor());
		existingBook.setTitle(bookRequest.getTitle());
		existingBook.setAvailableCopies(bookRequest.getAvailableCopies());
		Book updatedBook = bookRepository.save(existingBook);
		logBookAction(updatedBook.getId(), "UPDATED");
		BookResponseDTO bookResponse = bookMapper.mapToBookDTO(updatedBook);
		return bookResponse;
	}

	public void logBookAction(Long bookId, String action) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (auth != null && auth.isAuthenticated()) ? auth.getName() : "anonymous";

		BookAuditLog auditLog = new BookAuditLog();
		auditLog.setBookId(bookId);
		auditLog.setAction(action);
		auditLog.setPerformedBy(username);
		auditLog.setTimestamp(new Date());
		auditLogProducer.sendBookAuditLogs(auditLog);
	}

}
