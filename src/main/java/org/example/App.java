package org.example;

import org.example.config.DatabaseConnection;
import org.example.entities.Book;
import org.example.services.BookService;
import org.example.services.BookServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        DatabaseConnection.createEntityManagerFactory();
        BookService bookService = new BookServiceImpl();
        while (true){
            System.out.println("""
                    Press to 1 book save
                    Press to 2 save list of books
                    Press to 3 to get all list of books
                    Press to 4 to find the book by ID
                    Press to 5 to update the book
                    Press to 6 to delete the book
                    Press to 7 to clean the table 
                    Press to 8 to drop the table
                    Press to 9 to search books by name
                    """);
            switch (new Scanner(System.in).nextLine()){
                case "1" ->{
                    bookService.saveBook(
                            new Book(
                                    "Tarzan",
                                    "Charles Darwin",
                                    BigDecimal.valueOf(950)
                            ));
                }

                case "2" ->{
                    bookService.saveAllBooks(
                            List.of(
                                    new Book ("Martin Eedan", "Jack London",BigDecimal.valueOf(800)),
                                    new Book ("Kylym karytar bir kun", "Chyngyz Aitmatov",BigDecimal.valueOf(1000)),
                                    new Book ("Robinson Crusoe", "Daniel Defoe",BigDecimal.valueOf(800)),
                                    new Book ("Encyclopedia", "Abel Tasman",BigDecimal.valueOf(1300)),
                                    new Book ("Chemistry", "Dmitriy Mendeleev",BigDecimal.valueOf(1100))
                    ));
                }

                case "3" ->{
                    bookService.findAll().forEach(System.out::println);
                }
                case "4" ->{
                    System.out.println(" Input the ID to find the book: ");
                    try {
                        System.out.println(bookService.findById(new Scanner(System.in).nextLong()));
                    } catch (RuntimeException e) {
                        System.err.println(e.getMessage());
                    }
                }
                case "5" ->{
                    System.out.println("Input the books ID to update: ");
                    Long bookId = new Scanner(System.in).nextLong();
                    System.out.println("Input the name of book: ");
                    String newName = new Scanner(System.in).nextLine();
                    System.out.println("Write the author of book: ");
                    String author = new Scanner(System.in).nextLine();
                    System.out.println("Input the price of book: ");
                    BigDecimal price = new Scanner(System.in).nextBigDecimal();
                    System.out.println(bookService.updateBook(bookId, new Book(newName, author, price)));
                }
                case "6" ->{
                    System.out.println("Input the books ID to delete: ");
                    System.out.println(bookService.deleteBook(new Scanner(System.in).nextLong()));
                }
                case "7" ->{
                    System.out.println(bookService.clean());
                }
                case "8" ->{
                    System.out.println(bookService.drop());
                }
                case "9" ->{
                    System.out.println(" Write the books name to search: ");
                    System.out.println(bookService.searchByBooksName(new Scanner(System.in).nextLine()));
                }


            }
        }


    }
}
