package org.example.repositories;

import org.example.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepo {
    void save(Book book);

    void saveAll(List<Book> books);

    List<Book> findAll();

   Optional<Book> findById(Long bookID);

    Book updateBook(Long oldBookId, Book newBook);

    String deleteBook(Long bookId);

    String clean();

    String drop();

    List<Book> searchByBooksName(String bookName);

}
