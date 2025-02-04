package com.aladdin.demobooksite.serviceces;

import com.aladdin.demobooksite.dao.entity.Book;
import com.aladdin.demobooksite.dao.repository.BookRepository;
import com.aladdin.demobooksite.exceptions.ResourceNotFoundException;
import com.aladdin.demobooksite.model.dto.response.ResponseBookDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;


@Service
@RequiredArgsConstructor
public class BookWritableService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final EmailService mailSender;

    public ResponseBookDto saveBook(Book book) {
        bookRepository.save(book);
        String subject = "New information!";
        String text = "";
        FileSystemResource imageFile = new FileSystemResource(new File("C:/Users/Asus/Pictures/Success.png"));
        mailSender.sendEmail("aladdin19.11.21@gmail.com", subject, text, imageFile);

        return modelMapper.map(book, ResponseBookDto.class);
    }


    @Transactional
    public ResponseBookDto updateBookQuantity(Integer id, int quantity, double price, boolean stock) {
        Book findingBook = bookRepository
                .findById(id)
                .orElseThrow(() -> ResourceNotFoundException.of("There is no book about this İD! {0}", id));
        if (quantity > 0) {
            findingBook.setQuantity(quantity);
        }
        if (price > 0) {
            findingBook.setPrice(price);
        }
        findingBook.setInStock(stock);
        bookRepository.save(findingBook);
        return modelMapper.map(findingBook, ResponseBookDto.class);
    }

    @Transactional
    public void updateCustomBookSPrice(double checkPrice, double price) {
        int updateCount = bookRepository.updateCustomBooksPrice(checkPrice, price);
        if (updateCount == 0) {
            throw ResourceNotFoundException.of("There are no books in these settings! : {0}", checkPrice);
        }

    }

}
