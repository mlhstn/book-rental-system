package com.rentbook.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
        private Long id;
        private Long userId;
        private Long bookId;
        private int rating;
        private String commentText;
        private LocalDateTime commentDate;
}
