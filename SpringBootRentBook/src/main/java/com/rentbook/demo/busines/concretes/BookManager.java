package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.IBookService;
import com.rentbook.demo.core.config.Msg;
import com.rentbook.demo.core.config.exception.NotFoundException;
import com.rentbook.demo.core.config.exception.RecordAlreadyExistException;
import com.rentbook.demo.dao.BookRepository;
import com.rentbook.demo.dao.CategoryRepository;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class BookManager implements IBookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    public BookManager(CategoryRepository categoryRepository, ModelMapper modelMapper, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        Book existingBook = bookRepository.findByTitle(book.getTitle());

        if (Objects.nonNull(existingBook)){

            throw new RecordAlreadyExistException(existingBook.getId());
        }

        Long category_Id = book.getCategory().getId();
        Category category = categoryRepository.findById(category_Id)
                .orElseThrow(() -> new RuntimeException("kategori bulunamadı"));

        book.setCategory(category);
        book.setId(null);

        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        this.get(book.getId());
        return this.bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(()-> new RuntimeException("Kitap bulunamadı"));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalArgumentException("Kayıt bulunamadı! ID: " + id);
        }
        bookRepository.deleteById(id);
    }


    @Override
    public Book get(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        return bookRepository.searchByKeyword(keyword);
    }

}


