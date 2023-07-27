package org.example.services;

import org.example.entities.Book;
import org.example.repositories.BookRepo;
import org.example.repositories.BookRepository;

import java.util.List;

public class BookServiceImpl implements BookService {
    BookRepo bookRepo = new BookRepository();

    @Override
    public String saveBook(Book book) {
        bookRepo.save(book);
        return "Successfully saved book "+book.toString();
    }

    @Override
    public void saveAllBooks(List<Book> books) {
        bookRepo.saveAll(books);
    }

    @Override
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book findById(Long bookID) {
        Book book = bookRepo.findById(bookID).orElseThrow(() -> new RuntimeException(" Book with id " + bookID + " not found!!!"));
        return book;
    }

    @Override
    public Book updateBook(Long oldBookId, Book newBook) {
        return bookRepo.updateBook(oldBookId,newBook);
    }

    @Override
    public String deleteBook(Long bookId) {
        return bookRepo.deleteBook(bookId);
    }

    @Override
    public String clean() {
        return bookRepo.clean();
    }

    @Override
    public String drop() {
        return bookRepo.drop();
    }

    @Override
    public List<Book> searchByBooksName(String bookName) {
        return bookRepo.searchByBooksName(bookName);
    }
}
