package com.jloroz.blogrestapi.controller;

import com.jloroz.blogrestapi.payload.CommentDto;
import com.jloroz.blogrestapi.service.CommentService;
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
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;
    Logger log = LoggerFactory.getLogger(CommentController.class);

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity createComment(@PathVariable Long postId,
                                        @RequestBody CommentDto commentDto){
        log.info("Creating comment for id {} with {}", postId, commentDto.toString());
        return new ResponseEntity(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity getAllCommentsByPostId(@PathVariable Long postId){
        log.info("Getting all comments for postId {}", postId);
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity getCommentByCommentId(@PathVariable(value = "postId") Long postId,
                                                @PathVariable(value = "id") Long commentId){
        log.info("Getting all comments for postId {}", postId);
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity updateCommentByCommentId(@PathVariable(value = "postId") Long postId,
                                                   @PathVariable(value = "id") Long commentId,
                                                   @RequestBody CommentDto commentDto){
        log.info("Updating comments for postId {} with id {} with {}", postId, commentId, commentDto.toString());
        return ResponseEntity.ok(commentService.updateCommentById(postId, commentId, commentDto));
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity deleteComment(@PathVariable(value = "postId") Long postId,
                                        @PathVariable(value = "id") Long commentId){
        log.info("Deleting comments for postId {} with id {}", postId, commentId);
        return ResponseEntity.ok("Comment delete successfully");
    }
}

