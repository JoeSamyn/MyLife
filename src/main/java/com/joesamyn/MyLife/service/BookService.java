package com.joesamyn.MyLife.service;

import com.joesamyn.MyLife.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    void add(Book book, MultipartFile file);
    void delete(Book book);
    List<Book> getBookList();
    Book findById(long id);
}
