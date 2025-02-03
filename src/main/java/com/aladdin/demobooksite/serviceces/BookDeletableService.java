package com.aladdin.demobooksite.serviceces;

import com.aladdin.demobooksite.dao.entity.Book;
import com.aladdin.demobooksite.dao.repository.BookRepository;
import com.aladdin.demobooksite.exceptions.ResourceNotFoundException;
import com.aladdin.demobooksite.model.dto.response.ResponseBookDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookDeletableService {
    private final BookRepository bookRepository;
    private final EmailService mailSender;
    private final ModelMapper modelMapper;


    public ResponseBookDto deleteBookById(Integer id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            bookRepository.deleteById(id);
            return modelMapper.map(optionalBook.get(), ResponseBookDto.class);
        }
        throw ResourceNotFoundException.of("Not found is id! {0}", id);
    }

    @Transactional
    public void deleteBooksByAuthor(String author) {
        int deletedCount = bookRepository.deleteByAuthor(author);
        if (deletedCount > 0) {
            System.out.println("All books with the author " + author + " have been deleted!");
        } else {
            throw ResourceNotFoundException.of("There is no book wit the author! {0}", author);
        }
    }

    @Transactional
    public void deleteBookByTitle(String title) {
        int deletedCount = bookRepository.deleteByTitle(title);
        if (deletedCount > 0) {
            System.out.println("All books with the title " + title + " have been deleted!");
        } else {
            throw ResourceNotFoundException.of("There is no book with this title! {0} ", title);
        }
    }

    @Transactional
    public void deleteBooksByPriceLessThan(double price) {
        int deletedCount = bookRepository.deleteByPriceLessThan(price);
        if (deletedCount > 0) {
            System.out.println(deletedCount + " book(s) with price less than " + price + " successfully deleted!");
        } else {
            throw ResourceNotFoundException.of("No books found with price less than: {0}", price);
        }
    }

    @Transactional
    public void deleteBooksByStockStatus() {
        int deletedCount = bookRepository.deleteByInStockFalse();
        if (deletedCount > 0) {
            System.out.println(deletedCount + " out of stock book(s) removed!");
        } else {
            throw new ResourceNotFoundException("There is no book that is out of stock!");
        }
    }

    @Transactional
    @Scheduled(cron = "54 23 * * * ?")
    public void deleteAllBooks() {
        bookRepository.deleteAll();
        bookRepository.resetAutoIncrement();
        System.out.println("All books deleted and ID reset to 1.");

        String subject = "WARNING!";
        String text = "All books deleted! ";
        FileSystemResource imageFile = new FileSystemResource(new File("C:/Users/Asus/Pictures/DeletedNew.png"));
        mailSender.sendEmail("aladdin19.11.21@gmail.com", subject, text, imageFile);
    }


    public void deleteAllBooksAutomatically() {
        bookRepository.deleteAll();
    }

}
