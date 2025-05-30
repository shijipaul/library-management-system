package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.model.BookAuditLog;
import com.example.library.repository.BookAuditLogRepository;

@Service
public class AuditLogsService {
	@Autowired
	private BookAuditLogRepository bookAuditLogRepo;

	public List<BookAuditLog> getBookAuditLogs(Long bookId) {
		return bookAuditLogRepo.findByBookId(bookId);
	}
}
