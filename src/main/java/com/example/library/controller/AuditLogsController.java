package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.model.BookAuditLog;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/audits")
@Tag(name = "Audit Logs",description = "To Log the Actions like create,delete,update etc")
public class AuditLogsController {
	
	@Autowired
	private AuditLogsService auditLogsService;
	
	@GetMapping("/{bookId}/audit-logs")
	@Operation(summary = "retrieveBookAuditLgs",description = "To fetch all audit logs related to books")
	public ResponseEntity<List<BookAuditLog>> getBookAuditLogs(@PathVariable Long bookId){
		return ResponseEntity.ok(auditLogsService.getBookAuditLogs(bookId));
	}
}
