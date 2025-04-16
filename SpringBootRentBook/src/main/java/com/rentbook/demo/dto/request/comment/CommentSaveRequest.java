package com.rentbook.demo.dto.request.comment;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class CommentSaveRequest {

    private Long userId;
    private Long bookId;

    @Min(value = 1, message = "Puan en az 1 olmalı")
    @Max(value = 5, message = "Puan en fazla 5 olabilir")
    private int rating;

    @NotBlank(message = "yorum boş olamaz")
    @Size(max = 500, message = "yorum en fazla 500 karakter olabilir")
    private String commentText;

    public CommentSaveRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    @Min(value = 1, message = "Puan en az 1 olmalı")
    @Max(value = 5, message = "Puan en fazla 5 olabilir")
    public int getRating() {
        return rating;
    }

    public void setRating(@Min(value = 1, message = "Puan en az 1 olmalı") @Max(value = 5, message = "Puan en fazla 5 olabilir") int rating) {
        this.rating = rating;
    }

    public @NotBlank(message = "yorum boş olamaz") @Size(max = 500, message = "yorum en fazla 500 karakter olabilir") String getCommentText() {
        return commentText;
    }

    public void setCommentText(@NotBlank(message = "yorum boş olamaz") @Size(max = 500, message = "yorum en fazla 500 karakter olabilir") String commentText) {
        this.commentText = commentText;
    }
}
