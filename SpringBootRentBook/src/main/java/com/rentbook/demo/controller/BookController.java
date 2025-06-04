package com.rentbook.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentbook.demo.busines.abstracts.IBookService;
import com.rentbook.demo.core.modelMapper.IModelMapperService;
import com.rentbook.demo.dto.request.Book.BookSaveRequest;
import com.rentbook.demo.dto.request.Book.BookUpdateRequest;
import com.rentbook.demo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    @Autowired
    private final IBookService iBookService;
    private final IModelMapperService modelMapper;

    public BookController(IBookService iBookService, IModelMapperService modelMapper) {
        this.iBookService = iBookService;
        this.modelMapper = modelMapper;
    }


    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@RequestBody BookSaveRequest bookSaveRequest) throws JsonProcessingException {
        Book book = modelMapper.forRequest().map(bookSaveRequest, Book.class);
        Book savedBook = iBookService.saveBook(book);

        // JSON olarak düzgün dönüp dönmediğini görmek için
        System.out.println(new ObjectMapper().writeValueAsString(savedBook));

        return savedBook; // JSON olarak düzgün dönmeli
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Book updateBook(@PathVariable("id") Long id, @RequestBody BookUpdateRequest bookUpdateRequest) throws JsonProcessingException {
        Book existingBook = this.iBookService.getBookById(id); // Önce kitabı bul
        this.modelMapper.forRequest().map(bookUpdateRequest, existingBook); // Güncelleme işlemi
        Book updatedBook = this.iBookService.updateBook(existingBook); // Güncellenmiş kitabı kaydet

        // JSON olarak düzgün dönüp dönmediğini görmek için
        System.out.println(new ObjectMapper().writeValueAsString(updatedBook));

        return updatedBook; // Güncellenmiş kitabı JSON olarak döndür
    }



    @GetMapping("/findAll")
    public List<Book>getAllBooks(){
        return iBookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return iBookService.getBookById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        iBookService.deleteBook(id);
        return "Kitap başarıyla silindi";
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword) {
        return iBookService.searchBooks(keyword);
    }


}




