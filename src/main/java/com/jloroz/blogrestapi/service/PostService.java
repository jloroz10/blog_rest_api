package com.jloroz.blogrestapi.service;

import com.jloroz.blogrestapi.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto savePost(PostDto post);

    public List<PostDto> getAll();
}
