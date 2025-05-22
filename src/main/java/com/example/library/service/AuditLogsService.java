package com.example.library.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

	public void logBookAction(Long bookId, String action) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = (auth != null && auth.isAuthenticated()) ? auth.getName() : "anonymous";

		BookAuditLog auditLog = new BookAuditLog();
		auditLog.setBookId(bookId);
		auditLog.setAction(action);
		auditLog.setPerformedBy(username);
		auditLog.setTimestamp(new Date());

		bookAuditLogRepo.save(auditLog);
	}

}
