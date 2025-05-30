package com.example.library.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Data;
@Data
@Document(collection = "book_audit_logs")
public class BookAuditLog {
	@Id
	private String id;
	private Long bookId;
	private String action;//CREATED,DELETED,UPDATED
	private  String performedBy;
	private Date timestamp=new Date();

}
