package com.jloroz.blogrestapi.service;

import com.jloroz.blogrestapi.payload.PostDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {
    PostDto savePost(PostDto post);
    List<PostDto> getAll();
    PostDto getPostById(Long id);
    PostDto updatePost(Long id, PostDto postDto);

    void deletePost(Long id);
}
