package com.rentbook.demo.busines.abstracts;

import com.rentbook.demo.dto.request.comment.CommentSaveRequest;
import com.rentbook.demo.entity.Comment;

import java.util.List;

public interface ICommentService {

Comment saveComment(CommentSaveRequest saveRequest);

Comment updateComment(Long commendId, int rating, String commentText);

void deleteComment(Long commentId);

List<Comment> getCommentsByBook(Long bookId);

List<Comment> getCommentsByUser(Long userId);

Double getAverageRatingForBook(Long bookId);
}
