package com.jloroz.blogrestapi.service.impl;

import com.jloroz.blogrestapi.exception.ResourceNotFoundException;
import com.jloroz.blogrestapi.model.Post;
import com.jloroz.blogrestapi.payload.PostDto;
import com.jloroz.blogrestapi.repository.PostRepository;
import com.jloroz.blogrestapi.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    public PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto savePost(PostDto post) {
        log.info("Saving a post with {}",post.toString());
        // Converting DTO to Post
        Post newPost = mapPostDtoToPost(post);

        // Converting Entity to DTO
        Post response =  postRepository.save(newPost);
        log.info("response from service with {}",response.toString());

        return mapPostToPostDto(response);
    }

    @Override
    public List<PostDto> getAll() {
        log.info("Getting all the posts");
        List<Post> postList = postRepository.findAll();

        return postList.stream()
                .map(this::mapPostToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",String.valueOf(id)));
        return mapPostToPostDto(post);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(id)));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post response = postRepository.save(post);
        return mapPostToPostDto(response);

    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post","id",String.valueOf(id)));
        postRepository.delete(post);
    }

    private PostDto mapPostToPostDto(Post post){
        return new PostDto.PostDtoBuilder()
                .setId(post.getId())
                .setTitle(post.getTitle())
                .setDescription(post.getDescription())
                .setContent(post.getContent())
                .build();
    }

    private Post mapPostDtoToPost(PostDto postDto) {
        return new Post.PostBuilder()
                .setTitle(postDto.getTitle())
                .setDescription(postDto.getDescription())
                .setContent(postDto.getContent())
                .build();
    }
}
