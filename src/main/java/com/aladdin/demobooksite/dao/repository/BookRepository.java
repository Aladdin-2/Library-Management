package com.aladdin.demobooksite.dao.repository;

import com.aladdin.demobooksite.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {


    int deleteByAuthor(String author);

    int deleteByTitle(String title);


    int deleteByPriceLessThan(double price);

    int deleteByInStockFalse();

    @Modifying
    @Query(value = "ALTER TABLE books AUTO_INCREMENT = 1", nativeQuery = true)
    void resetAutoIncrement();


    @Query("SELECT b FROM Book b WHERE " +
            "(:author IS NULL OR LOWER(b.author)=LOWER(:author)) AND " +
            "(:title IS NULL OR LOWER(b.title)=LOWER(:title))")
    List<Book> findBookByAuthorAndTitle(@Param("author") String author, @Param("title") String title);


    @Query("SELECT b FROM Book b WHERE " +
            "b.price>= COALESCE(:minPrice,0) and " +
            "b.price<= COALESCE(:maxPrice,999999999) ")
    List<Book> filterBookByPrice(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    List<Book> searchByInStock(boolean stock);

    List<Book> searchByGenre(String genre);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.quantity=:quantity WHERE b.id=:id")
    int updateBookQuantity(@Param("id") Integer id, @Param("quantity") int quantity);

    @Transactional
    @Modifying
    @Query("UPDATE Book b SET b.price=:newPrice WHERE b.price<=:checkPrice")
    int updateCustomBooksPrice(@Param("checkPrice") double checkPrice,
                               @Param("newPrice") double newPrice);
}
