package com.mkumar.blogpost.services;


import com.mkumar.blogpost.payload.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(CommentDTO CommentDTO, long postId);

    List<CommentDTO> getCommentsByPostId(long postId);

    CommentDTO getCommentById(long postId, long commentId);

    CommentDTO updateComment(long postId, long commentId, CommentDTO CommentDTO);

    void deleteComment(long postId, long commentId);
}