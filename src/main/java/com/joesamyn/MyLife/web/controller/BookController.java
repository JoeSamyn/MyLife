package com.joesamyn.MyLife.web.controller;

import com.joesamyn.MyLife.model.Book;
import com.joesamyn.MyLife.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;


    @RequestMapping("/")
    public String allBooks(Model model){
        List<Book> books = bookService.getBookList();
        model.addAttribute("books", books);
        //System.out.println(books.get(0).getTitle());
        return "books/index";
    }


    @RequestMapping("/add")
    public String addBook(Model model){
        if(!model.containsAttribute("book"))
            model.addAttribute("book", new Book());
        model.addAttribute("action", "/book");

        return "books/form";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String saveBook(Book book, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes){

        bookService.add(book, file);
        //System.out.println(book.getTitle());
        return String.format("redirect:books/%s", book.getId());
    }

    @RequestMapping("/books/{bookId}")
    public String bookDetails(Model model, @PathVariable Long bookId){
        model.addAttribute("book", bookService.findById(bookId));
        model.addAttribute("buttonText", "Delete");
        return "books/details";
    }

    @RequestMapping("/books/{bookId}.img")
    @ResponseBody
    public byte[] bookImage(@PathVariable Long bookId){
        return bookService.findById(bookId).getBytes();
    }

    @RequestMapping(value = "/books/{bookId}/delete", method = RequestMethod.POST)
    public String deleteBook(@PathVariable Long bookId){
        Book book = bookService.findById(bookId);
        bookService.delete(book);

        return "redirect:/";
    }

    @RequestMapping(value = "/books/{bookId}/edit")
    public String editBook(@PathVariable Long bookId, Model model){
        if(!model.containsAttribute("book"))
            model.addAttribute("book", bookService.findById(bookId));
        model.addAttribute("action", String.format("/books/%s", bookId));
        return "books/form";
    }

    @RequestMapping(value = "/books/{bookId}", method = RequestMethod.POST)
    public String updateBook(@Valid Book book, BindingResult result, MultipartFile file){
        if(result.hasErrors())
            System.err.println("Errors in book");
        System.out.println(book.toString());
        bookService.add(book, file);
        return String.format("redirect:/books/%s", book.getId());
    }



}
