package com.aladdin.demobooksite.serviceces;

import com.aladdin.demobooksite.dao.entity.Book;
import com.aladdin.demobooksite.dao.repository.BookRepository;
import com.aladdin.demobooksite.exceptions.IllegalArgumentCastException;
import com.aladdin.demobooksite.exceptions.ResourceNotFoundException;
import com.aladdin.demobooksite.model.dto.response.ResponseBookDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookReadableService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public ResponseBookDto getBook(Integer id) {
        Book findingBook = bookRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("There are no books matching this id: {0}", id));
        return modelMapper.map(findingBook, ResponseBookDto.class);
    }

    public List<ResponseBookDto> getAllBook() {
        List<ResponseBookDto> responseBookList = new ArrayList<>();
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No book");
        } else {
            books.forEach(book -> {
                responseBookList.add(modelMapper.map(book, ResponseBookDto.class));
            });
            return responseBookList;
        }

    }

    public List<ResponseBookDto> getBookWithParam(String author, String title) {
        List<Book> foundBooks = bookRepository.findBookByAuthorAndTitle(author, title);

        List<ResponseBookDto> responseBooks = new ArrayList<>();

        if (foundBooks.isEmpty()) {
            throw ResourceNotFoundException.of("There is no book with this name {0} and title {1} :", author, title);
        } else {
            foundBooks.forEach(book -> {
                responseBooks.add(modelMapper.map(book, ResponseBookDto.class));
            });
            return responseBooks;
        }
    }

    public List<ResponseBookDto> sortByPriceDescendingAndIncreasing(String sortPriceBy) {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            throw ResourceNotFoundException.of("There are no books in these settings! {0} ", sortPriceBy);
        }

        if (!"descending".equalsIgnoreCase(sortPriceBy) && !"increasing".equalsIgnoreCase(sortPriceBy)) {
            throw IllegalArgumentCastException.of("Invalid sort option. Use 'descending' or 'increasing'. :{0}", sortPriceBy);
        }

        return books.stream()
                .sorted("descending".equalsIgnoreCase(sortPriceBy)
                        ? Comparator.comparing(Book::getPrice).reversed()
                        : Comparator.comparing(Book::getPrice))
                .map(book -> modelMapper.map(book, ResponseBookDto.class))
                .toList();
    }


    public List<ResponseBookDto> sortByQuantity(String sortByQuantity) {
        List<Book> books = bookRepository.findAll();

        if (books.isEmpty()) {
            throw ResourceNotFoundException.of("There are no books in these settings! {0} ", sortByQuantity);
        }

        if (!sortByQuantity.equalsIgnoreCase("descending") && !sortByQuantity.equalsIgnoreCase("increasing")) {
            throw IllegalArgumentCastException.of("Invalid sort option. Use 'descending' or 'increasing'. :{0}", sortByQuantity);
        }
        return books.stream()
                .sorted("descending".equalsIgnoreCase(sortByQuantity)
                        ? Comparator.comparingInt(Book::getQuantity).reversed()
                        : Comparator.comparingInt(Book::getQuantity))
                .map(book -> modelMapper.map(book, ResponseBookDto.class))
                .toList();
    }

    public List<ResponseBookDto> filterBookByPrice(Double minPrice, Double maxPrice) {
        List<Book> books = bookRepository.filterBookByPrice(minPrice, maxPrice);

        if (books.isEmpty()) {
            throw ResourceNotFoundException.of("There are no books in these settings! {0},{1}: ", minPrice, maxPrice);
        }
        if (minPrice > maxPrice) {
            throw IllegalArgumentCastException.of("Minimum price :{0} cannot be greater than maximum price {1}.", minPrice, maxPrice);
        }
        return books.stream().map(book -> modelMapper.map(book, ResponseBookDto.class)).toList();
    }

    public List<ResponseBookDto> filterBookByGenre(String genre) {
        System.out.println("Choice genre! ");
        System.out.println("-- Fantasy -- Programming -- Algorithms -- Autobiography -- Adventure -- Young Adult (YA) --");
        List<Book> books = bookRepository.searchByGenre(genre);
        if (books.isEmpty()) {
            throw ResourceNotFoundException.of("There are no books in these settings! {0} ", genre);
        }
        return books.stream().map(book -> modelMapper.map(book, ResponseBookDto.class)).toList();
    }

    public List<ResponseBookDto> stockCheckFilter(boolean stock) {
        List<Book> books = bookRepository.searchByInStock(stock);
        if (books.isEmpty()) {
            throw ResourceNotFoundException.of("There are no books in these settings! {0} ", stock);
        }
        return books.stream().map(book -> modelMapper.map(book, ResponseBookDto.class)).toList();
    }


}
