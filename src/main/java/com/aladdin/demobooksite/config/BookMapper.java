package com.aladdin.demobooksite.config;

import com.aladdin.demobooksite.dao.entity.Book;
import com.aladdin.demobooksite.model.dto.response.ResponseBookDto;
import org.springframework.stereotype.Component;

// Bu üsül daha compleks həllər üçün nəzərdə tutulub
@Component
public class BookMapper {

    public ResponseBookDto toDto(Book book) {
        return new ResponseBookDto(
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getCommentsForBook(),
                book.getPrice(),
                book.getQuantity(),
                book.isInStock());
    }

    public Book fromDto(ResponseBookDto bookDto) {
        return new Book(
                null,
                bookDto.getTitle(),
                bookDto.getAuthor(),
                bookDto.getGenre(),
                bookDto.getCommentsForBook(),
                bookDto.getPrice(),
                bookDto.getQuantity(),
                bookDto.isInStock(),
                null);
    }

}
