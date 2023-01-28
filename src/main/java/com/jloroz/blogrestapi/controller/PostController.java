package com.jloroz.blogrestapi.controller;

import com.jloroz.blogrestapi.payload.PostDto;
import com.jloroz.blogrestapi.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    public PostService postService;
    Logger log = LoggerFactory.getLogger(PostController.class);

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity savePost(@RequestBody PostDto post){
        log.info("calling savePost with {}", post.toString());
        return new ResponseEntity(postService.savePost(post),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllPosts(){
        log.info("calling getAllPosts");
        return ResponseEntity.ok(this.postService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getPostById(@PathVariable Long id){
        log.info("Getting post by id with {}",id);
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePost(@PathVariable Long id, @RequestBody PostDto postDto){
        log.info("Updating post with id {} with {}",id,postDto.toString());
        return  ResponseEntity.ok(postService.updatePost(id, postDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity("Post delete successfully", HttpStatus.OK);
    }
}
