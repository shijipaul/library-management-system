package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
	
	@NotBlank(message = "Title is required")
    private String title;
	
	@NotBlank(message = "Author is required")
    private String author;
	
	@Positive(message = "Available copies must be greater than 0")
    private int availableCopies;
}
