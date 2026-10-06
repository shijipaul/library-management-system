package com.example.library.exception;

import java.time.Instant;
import java.util.Map;

public record ErrorResponse(Instant timesatmp,
		int status,
		String error,
		String message,
		String path,
		Map<String,String> errors) {
	
}
