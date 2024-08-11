package com.mkumar.blogpost.controllers;


import com.mkumar.blogpost.payload.CommentDTO;
import com.mkumar.blogpost.services.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO commentDTO,
                                                    @PathVariable(value = "postId") long postId) {
        return new ResponseEntity<>(commentService.createComment(commentDTO, postId), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("postId") long postId,
                                                     @PathVariable("id") long commentId) {
        return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable("postId") long postId,
                                                    @PathVariable("id") long commentId,
                                                    @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDTO), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("postId") long postId,
                                                @PathVariable("id") long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment was deleted successfully", HttpStatus.OK);
    }

}