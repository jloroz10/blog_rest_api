package com.jloroz.blogrestapi.service.impl;

import com.jloroz.blogrestapi.model.Post;
import com.jloroz.blogrestapi.payload.PostDto;
import com.jloroz.blogrestapi.repository.PostRepository;
import com.jloroz.blogrestapi.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    public PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto savePost(PostDto post) {

        // Converting DTO to Post
        Post newPost = new Post.PostBuilder()
                .setTitle(post.getTitle())
                .setDescription(post.getDescription())
                .setContent(post.getContent())
                .build();

        // Converting Entity to DTO
        Post response =  postRepository.save(newPost);

        return new PostDto.PostDtoBuilder()
                .setId(response.getId())
                .setTitle(response.getTitle())
                .setDescription(response.getDescription())
                .setContent(response.getContent())
                .build();
    }
}
