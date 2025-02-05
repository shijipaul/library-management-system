package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Book;
import com.example.library.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping
	public ResponseEntity<?> addBook(@Valid @RequestBody Book book , BindingResult result) {
		
		 if (result.hasErrors()) {
	            StringBuilder errorMessage = new StringBuilder();
	            List<ObjectError> errors = result.getAllErrors();
	            for (ObjectError error : errors) {
	                errorMessage.append(error.getDefaultMessage()).append(", ");
	            }
	            return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
	        }
		return new ResponseEntity<>(bookService.addBook(book),HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBook(@Valid @RequestBody Book book, @PathVariable Long id, BindingResult result) {
		if (result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			List<ObjectError> errors = result.getAllErrors();
			for (ObjectError error : errors) {
				errorMessage.append(error.getDefaultMessage()).append(", ");
			}
			return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(bookService.updateBook(book, id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Book getBook(@PathVariable Long id) {
		return bookService.getBook(id);
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}

}