package com.aladdin.demobooksite.controllers;

import com.aladdin.demobooksite.model.dto.response.ResponseBookDto;
import com.aladdin.demobooksite.serviceces.BookReadableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "aladdin/site/book")
@RequiredArgsConstructor
public class BookReadableController {
    private final BookReadableService bookReadableService;

    @GetMapping(path =  "/find/{id}")
    public ResponseBookDto getBook(@PathVariable(name = "id") Integer id) {
        return bookReadableService.getBook(id);
    }

    @GetMapping(path = "/get-all")
    public List<ResponseBookDto> getAllBook() {
        return bookReadableService.getAllBook();
    }


    @GetMapping(path = "/get/param")
    public List<ResponseBookDto> getBookWithParam(@RequestParam(name = "author", required = false) String author,
                                                  @RequestParam(name = "title", required = false) String title) {
        return bookReadableService.getBookWithParam(author, title);
    }

    @GetMapping(path = "/get/{sortPriceBy}/price")
    public List<ResponseBookDto> sortByPriceDescendingAndIncreasing(@PathVariable(name = "sortPriceBy") String sortPriceBy) {
        return bookReadableService.sortByPriceDescendingAndIncreasing(sortPriceBy);
    }

    @GetMapping(path = "/get-book-sort-by-quantity/{sort}")
    public List<ResponseBookDto> sortByQuantity(@PathVariable(name = "sort") String sort) {
        return bookReadableService.sortByQuantity(sort);
    }

    @GetMapping(path = "/filter-book-by-price")
    public List<ResponseBookDto> filterBookByPrice(@RequestParam(name = "minPrice", required = false) Double minPrice,
                                                   @RequestParam(name = "maxPrice", required = false) Double maxPrice) {
        return bookReadableService.filterBookByPrice(minPrice, maxPrice);
    }

    @GetMapping(path = "/filter/{check}/stock")
    public List<ResponseBookDto> stockCheckFilter(@PathVariable(name = "check") boolean check) {
        return bookReadableService.stockCheckFilter(check);
    }

    @GetMapping(path = "/filter/{genre}/genre")
    public List<ResponseBookDto> filterBookByGenre(@PathVariable(name = "genre") String genre) {
        return bookReadableService.filterBookByGenre(genre);
    }
}

