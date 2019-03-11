package com.joesamyn.MyLife.dao;

import com.joesamyn.MyLife.model.Book;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private SessionFactory sessionFactory;

    // Where we perform database queries.

    @Override
    public void add(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.saveOrUpdate(book);
            tx.commit();
        } catch (HibernateException e){
            if(tx == null){tx.rollback();}
            System.err.println(e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void delete(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(book);
            tx.commit();
        } catch (HibernateException e){
            if(tx == null){ tx.rollback();}
            System.err.println(e.getMessage());
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Override
    public List<Book> getBookList() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = builder.createQuery(Book.class);
        cq.from(Book.class);
        List<Book> books = session.createQuery(cq).getResultList();
        session.close();
        return books;
    }

    @Override
    public Book findById(long id) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        Hibernate.initialize(book);
        session.close();
        return book;
    }
}
