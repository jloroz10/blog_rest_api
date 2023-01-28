package com.jloroz.blogrestapi.service;

import com.jloroz.blogrestapi.payload.PostDto;
import com.jloroz.blogrestapi.payload.PostResponse;

public interface PostService {
    PostDto savePost(PostDto post);
    PostResponse getAll(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(Long id);
    PostDto updatePost(Long id, PostDto postDto);
    void deletePost(Long id);
}
