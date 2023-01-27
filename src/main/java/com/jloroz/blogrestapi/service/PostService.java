package com.jloroz.blogrestapi.service;

import com.jloroz.blogrestapi.payload.PostDto;

public interface PostService {
    public PostDto savePost(PostDto post);
}
