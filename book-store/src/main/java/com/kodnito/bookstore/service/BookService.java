package com.kodnito.bookstore.service;
import com.kodnito.bookstore.entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
@ApplicationScoped
public class BookService {
    @PersistenceContext(unitName = "restapi_PU")
    EntityManager em;
    public List getAll() {
        List<Book> books = em.createNamedQuery("Player.findAll", Book.class)
                .getResultList();
        return books != null ? books : new ArrayList<>();
    }
    public Book findById(Long id) {
        return em.find(Book.class, id);
    }
    @Transactional
    public void update(Book book) {
        em.merge(book);
    }
    @Transactional
    public void create(Book book) {
        em.persist(book);
    }
    @Transactional
    public void delete(Book book) {
        if (!em.contains(book)) {
            book = em.merge(book);
        }
        em.remove(book);
    }
}