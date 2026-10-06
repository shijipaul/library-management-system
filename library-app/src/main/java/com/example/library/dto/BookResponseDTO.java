package com.example.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDTO {
	private Long id;
    private String title;
    private String author;
    private int availableCopies;
}
