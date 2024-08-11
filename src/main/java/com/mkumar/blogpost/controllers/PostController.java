package com.mkumar.blogpost.controllers;


import com.mkumar.blogpost.payload.PostDTO;
import com.mkumar.blogpost.payload.PostResponse;
import com.mkumar.blogpost.services.PostService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ) {
        return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long postId) {
        return new ResponseEntity<>(postService.getPostById(postId), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDto,
                                              @PathVariable("id") Long postId) {
        return new ResponseEntity<>(postService.updatePost(postDto, postId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> searchPosts(@RequestParam("query") String query) {
        return new ResponseEntity<>(postService.searchPosts(query), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>("Post was deleted successfully", HttpStatus.NO_CONTENT);
    }

}
