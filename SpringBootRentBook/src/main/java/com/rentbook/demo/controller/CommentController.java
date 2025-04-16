package com.rentbook.demo.controller;

import com.rentbook.demo.busines.abstracts.ICommentService;
import com.rentbook.demo.dto.request.comment.CommentSaveRequest;
import com.rentbook.demo.dto.response.CommentResponse;
import com.rentbook.demo.entity.Comment;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/comments")
public class CommentController {

    private final ICommentService commentService;
    private final ModelMapper modelMapper;

    public CommentController(ICommentService commentService,
                             ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentResponse> saveComment(@Valid @RequestBody CommentSaveRequest saveRequest){

        Comment comment = commentService.saveComment(saveRequest);

        CommentResponse response = modelMapper.map(comment, CommentResponse.class);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commendId,
            @Valid @RequestBody CommentSaveRequest saveRequest
    ) {
        Comment updated = commentService.updateComment(
                commendId,
                saveRequest.getRating(),
                saveRequest.getCommentText() );

        CommentResponse response = modelMapper.map(updated, CommentResponse.class);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByBook(@PathVariable Long bookId) {

        List<Comment> comments = commentService.getCommentsByBook(bookId);
        List<CommentResponse> responses = comments.stream()
                .map(r -> modelMapper.map(r, CommentResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByUser(@PathVariable Long userId) {

        List<Comment> comments = commentService.getCommentsByUser(userId);
        List<CommentResponse> responses = comments.stream()
                .map(r -> modelMapper.map(r, CommentResponse.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/book/{bookId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long bookId) {
        Double average = commentService.getAverageRatingForBook(bookId);
        return ResponseEntity.ok(average);
    }

    }












