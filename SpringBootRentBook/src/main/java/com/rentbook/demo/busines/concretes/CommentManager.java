package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.IBookService;
import com.rentbook.demo.busines.abstracts.ICommentService;
import com.rentbook.demo.busines.abstracts.IUserService;
import com.rentbook.demo.dao.CommentRepository;
import com.rentbook.demo.dto.request.comment.CommentSaveRequest;
import com.rentbook.demo.dto.response.CommentResponse;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Comment;
import com.rentbook.demo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentManager implements ICommentService {

    private final CommentRepository commentRepository;
    private final IUserService userService;
    private final IBookService bookService;
    private final ModelMapper modelMapper;

    public CommentManager(CommentRepository commentRepository,
                          IUserService userService,
                          IBookService bookService,
                          ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Comment saveComment(CommentSaveRequest saveRequest) {

        User user  = userService.getUserById(saveRequest.getUserId());
        Book book = bookService.getBookById(saveRequest.getBookId());

        Comment comment = modelMapper.map(saveRequest, Comment.class);

        //  İş kuralları (opsiyonel): aynı kullanıcı aynı kitaba birden fazla yorum yapamasın
        if (commentRepository.existsByUserAndBook(user, book)) {
            throw new IllegalStateException("Bu kullanıcı zaten bu kitap için yorum yapmış.");
        }

        comment.setUser(user);
        comment.setBook(book);

        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commendId, int rating, String commentText) {

        Comment comment = commentRepository.findById(commendId).
                orElseThrow(() -> new RuntimeException("yorum bulunamadı"));

        comment.setRating(rating);
        comment.setCommentText(commentText);


        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {

        if (commentRepository.existsById(id)){
            throw new IllegalArgumentException("Kayıt bulunamadı! ID: " + id);
        }
        commentRepository.deleteById(id);

    }

    @Override
    public List<Comment> getCommentsByBook(Long bookId) {

        Book book = bookService.getBookById(bookId);

        return commentRepository.findByBookOrderByCommentDateDesc(book);
    }

    @Override
    public List<Comment> getCommentsByUser(Long userId) {

        User user = userService.getUserById(userId);
        return commentRepository.findByUserOrderByCommentDateDesc(user);
    }

    @Override
    public Double getAverageRatingForBook(Long bookId) {

        Book book = bookService.getBookById(bookId);

        Double avg = commentRepository.findAverageRatingByBook(book);

        return avg != null ? avg : 0.0;
    }
}
