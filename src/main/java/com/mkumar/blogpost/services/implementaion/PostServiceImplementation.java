package com.mkumar.blogpost.services.implementaion;


import com.mkumar.blogpost.exception.ResourceNotFoundException;
import com.mkumar.blogpost.models.Post;
import com.mkumar.blogpost.payload.PostDTO;
import com.mkumar.blogpost.payload.PostResponse;
import com.mkumar.blogpost.repositories.PostRepository;
import com.mkumar.blogpost.services.PostService;
import lombok.AllArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostServiceImplementation implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO PostDTO) {
        Post post = modelMapper.map(PostDTO, Post.class);
        Post newPost = postRepository.save(post);
        return modelMapper.map(newPost, PostDTO.class);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        // Create a Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        // Retrieve a page of posts
        Page<Post> postList = postRepository.findAll(pageable);
        // Get content for page object
        List<Post> listOfPost = postList.getContent();
        List<PostDTO> content = listOfPost.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(postList.getNumber());
        postResponse.setPageSize(postList.getSize());
        postResponse.setTotalElements(postList.getTotalElements());
        postResponse.setTotalPages(postList.getTotalPages());
        postResponse.setLast(postList.isLast());
        return postResponse;
    }

    @Override
    public PostDTO getPostById(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return modelMapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO PostDTO, long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(PostDTO.getTitle());
        post.setContent(PostDTO.getContent());
        post.setDescription(PostDTO.getDescription());
        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostDTO.class);
    }

    @Override
    public List<PostDTO> searchPosts(String query) {
        List<Post> postList = postRepository.searchPosts(query);
        return postList.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.deleteById(id);
    }
}
