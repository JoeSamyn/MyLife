package com.joesamyn.MyLife.service;


import com.joesamyn.MyLife.dao.BookDao;
import com.joesamyn.MyLife.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;

    @Override
    public void add(Book book, MultipartFile file) {

        try {
            book.setBytes(file.getBytes());
            bookDao.add(book);
        } catch (IOException e){
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Book book) {
        bookDao.delete(book);
    }

    @Override
    public List<Book> getBookList() {
        return bookDao.getBookList();
    }

    @Override
    public Book findById(long id) {
        return bookDao.findById(id);
    }
}
