package com.mkumar.blogpost.repositories;

import com.mkumar.blogpost.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE " +
            "p.title LIKE CONCAT('%', :query, '%') OR " +
            "p.description LIKE CONCAT('%', :query, '%') OR " +
            "p.content LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);
}
