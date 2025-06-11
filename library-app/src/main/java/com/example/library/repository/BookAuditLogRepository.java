package com.example.library.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.library.model.BookAuditLog;
@Repository
public interface BookAuditLogRepository extends MongoRepository<BookAuditLog,String>{
	List<BookAuditLog> findByBookId(Long bookId);

}
