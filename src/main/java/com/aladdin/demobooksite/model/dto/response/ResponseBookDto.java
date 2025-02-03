package com.aladdin.demobooksite.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResponseBookDto {

    private String title;

    private String author;

    private String genre;

    private String commentsForBook;

    private double price;

    private int quantity;

    private boolean inStock;

}
