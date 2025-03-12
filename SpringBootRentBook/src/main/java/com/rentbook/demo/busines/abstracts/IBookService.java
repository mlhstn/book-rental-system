package com.rentbook.demo.busines.abstracts;
import com.rentbook.demo.entity.Book;

import java.util.List;

public interface IBookService {
    Book saveBook(Book book);

    Book updateBook(Book book);

    List<Book>getAllBooks();

    Book getBookById(Long id);

    void deleteBook(Long id);

    Book get(Long id);

    List<Book> searchBooks(String keyword);
}
