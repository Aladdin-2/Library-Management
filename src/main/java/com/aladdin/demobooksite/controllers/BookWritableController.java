package com.aladdin.demobooksite.controllers;

import com.aladdin.demobooksite.dao.entity.Book;
import com.aladdin.demobooksite.model.dto.response.ResponseBookDto;
import com.aladdin.demobooksite.serviceces.BookWritableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "aladdin/site/book")
public class BookWritableController {

    private final BookWritableService bookWritableService;

    @PostMapping(path = "/book")
    public ResponseBookDto saveBook(@RequestBody Book book) {
        return bookWritableService.saveBook(book);
    }

    @PatchMapping(path = "/update-book/{id}/id")
    public ResponseBookDto updateBookQuantity(@PathVariable(name = "id") Integer id,
                                              @RequestParam(name = "quantity", required = false) int quantity,
                                              @RequestParam(name = "price", required = false) double price,
                                              @RequestParam(name = "stock", required = false) boolean stock) {
        return bookWritableService.updateBookQuantity(id, quantity, price, stock);
    }

    @PatchMapping(path = "/set-prices")
    public void updateCustomBookSPrice(@RequestParam(name = "checkPrice") double checkPrice,
                                       @RequestParam(name = "price") double price) {
        bookWritableService.updateCustomBookSPrice(checkPrice, price);
    }
}
