package com.example.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.library.model.BookAuditLog;
import com.example.library.repository.BookAuditLogRepository;

@Service
public class BookAuditLogKafkaConsumer {
	
@Autowired
private BookAuditLogRepository auditLogRepo;

@KafkaListener(topics = "book_audit_log",groupId = "audit-group")
public void consumeBookAuditLogs(BookAuditLog log) {
	auditLogRepo.save(log);
	System.out.println("saved audit log from Kafka :"+log);
}
}
