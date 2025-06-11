package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.library.model.BookAuditLog;
@Service
public class BookAuditLogKafkaProducer {
	
	@Autowired
	private KafkaTemplate<String, BookAuditLog> kafkaTemplate;
	
	public void sendBookAuditLogs(BookAuditLog log) {
		kafkaTemplate.send("book_audit_log", log);
	}

}
