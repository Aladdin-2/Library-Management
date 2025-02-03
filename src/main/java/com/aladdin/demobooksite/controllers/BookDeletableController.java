package com.aladdin.demobooksite.controllers;

import com.aladdin.demobooksite.model.dto.response.ResponseBookDto;
import com.aladdin.demobooksite.serviceces.BookDeletableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "aladdin/site/book")
@RequiredArgsConstructor
public class BookDeletableController {

    private final BookDeletableService bookDeletableService;

    @DeleteMapping(path = "/delete/{id}/id")
    public ResponseBookDto deleteBookById(@PathVariable(name = "id") Integer id) {
        return bookDeletableService.deleteBookById(id);
    }

    @DeleteMapping(path = "/delete/{author}/author/")
    public void deleteBooksByAuthor(@PathVariable(name = "author") String author) {
        bookDeletableService.deleteBooksByAuthor(author);
    }

    @DeleteMapping(path = "/delete/{title}/title")
    public void deleteBookByTitle(@PathVariable(name = "title") String title) {
        bookDeletableService.deleteBookByTitle(title);
    }

    @DeleteMapping(path = "/delete/{price}/price")
    public void deleteBooksByPriceLessThan(@PathVariable(name = "price") double price) {
        bookDeletableService.deleteBooksByPriceLessThan(price);
    }

    @DeleteMapping(path = "/delete-status-false")
    void deleteBooksByStockStatus() {
        bookDeletableService.deleteBooksByStockStatus();
    }


    @DeleteMapping(path = "/delete-all")
    public void deleteAllBooks() {
        bookDeletableService.deleteAllBooks();
    }
}
