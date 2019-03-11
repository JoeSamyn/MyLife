package com.joesamyn.MyLife.dao;

import com.joesamyn.MyLife.model.Book;

import java.util.List;

public interface BookDao {

    void add(Book book);
    void delete(Book book);
    List<Book> getBookList();
    Book findById(long id);
}
