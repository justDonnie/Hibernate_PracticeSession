package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import org.example.config.DatabaseConnection;
import org.example.entities.Book;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class BookRepository implements BookRepo,AutoCloseable {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.createEntityManagerFactory();
    private final SessionFactory sessionFactory = DatabaseConnection.createSessionFactory();
    @Override
    public void save(Book book) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    @Override
    public void saveAll(List<Book> books) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        books.forEach(entityManager::persist);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public List<Book> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Book> resultList = entityManager.createQuery("""
                select b from Book b
                """, Book.class).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return resultList;
    }

    @Override
    public Optional<Book> findById(Long bookID) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, bookID);//--1 variant
        entityManager.getTransaction().commit();
        entityManager.close();
        return Optional.ofNullable(book) ;
    }

    @Override
    public Book updateBook(Long oldBookId, Book newBook) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String hql = " update Book b set b.name = :name, b.author=:author, b.price = :price where b.id = :oldBookId";
        Query query = entityManager.createQuery(hql);
        query.setParameter("name",newBook.getName());
        query.setParameter("author",newBook.getAuthor());
        query.setParameter("price",newBook.getPrice());
        int i = query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return newBook;
    }

    @Override
    public String deleteBook(Long bookId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Book book = entityManager.find(Book.class, bookId);
        entityManager.remove(book);
        entityManager.getTransaction().commit();
        entityManager.close();
        return book.getName()+" is successfully deleted!!!";

    }

    @Override
    public String clean() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String hql = "delete from Book b";
        Query query = entityManager.createQuery(hql);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Table is successfully deleted!!!";
    }

    @Override
    public String drop() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String hql = "drop table Book b";
        Query query = entityManager.createNativeQuery(hql);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        return "Table is successfully dropped";
    }

    @Override
    public List<Book> searchByBooksName(String bookName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Book>books = new ArrayList<>();
        books = entityManager.createQuery("""
                select b from Book b where b.name=:bookName
                """, Book.class).setParameter("bookName", bookName).getResultList();
        entityManager.getTransaction().commit();
        entityManager.close();
        return books;
    }



    @Override
    public void close() throws Exception {
        entityManagerFactory.close();
        sessionFactory.close();
    }
}




























//                Book book = entityManager.createQuery(""" --findById
//                select b from Book b where b.id=:id
//                """, Book.class).setParameter("id", bookID).getSingleResult();



//        Book currentBook = entityManager.find(Book.class,oldBookId); --Update
//        currentBook.setName(newBook.getName());
//        currentBook.setAuthor(newBook.getAuthor());
//        currentBook.setPrice(newBook.getPrice());


