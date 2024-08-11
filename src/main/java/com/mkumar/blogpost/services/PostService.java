package com.mkumar.blogpost.services;
import com.mkumar.blogpost.payload.PostDTO;
import com.mkumar.blogpost.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO PostDTO);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO PostDTO, long id);

    List<PostDTO> searchPosts(String query);

    void deletePost(long id);
}