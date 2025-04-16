package com.rentbook.demo.dao;

import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Comment;
import com.rentbook.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    // Bir kitaba ait tüm yorumları getir (tarih sıralı)
    List<Comment> findByBookOrderByCommentDateDesc(Book book);

    // Bir kullanıcının yaptığı tüm yorumları getir (tarih sıralı)
    List<Comment> findByUserOrderByCommentDateDesc(User user);

    // Bir kitabın ortalama puanını hesapla
    @Query("SELECT AVG(r.rating) FROM Comment r WHERE r.book = :book")
    Double findAverageRatingByBook(@Param("book") Book book);

    boolean existsByUserAndBook(User user, Book book);
}

