package org.example.services;

import org.example.entities.Book;

import java.util.List;

public interface BookService {
    String saveBook(Book book);

    void saveAllBooks(List<Book> books);

    List<Book> findAll();

    Book findById(Long bookID);

    Book updateBook(Long oldBookId, Book newBook);

    String deleteBook(Long bookId);

    String clean();

    String drop();

    List<Book> searchByBooksName(String bookName);
}
