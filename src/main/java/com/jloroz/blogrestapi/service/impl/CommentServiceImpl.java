package com.jloroz.blogrestapi.service.impl;

import com.jloroz.blogrestapi.exception.BlogApiException;
import com.jloroz.blogrestapi.exception.ResourceNotFoundException;
import com.jloroz.blogrestapi.model.Comment;
import com.jloroz.blogrestapi.model.Post;
import com.jloroz.blogrestapi.payload.CommentDto;
import com.jloroz.blogrestapi.repository.CommentRepository;
import com.jloroz.blogrestapi.repository.PostRepository;
import com.jloroz.blogrestapi.service.CommentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {
        log.info("Saving comment with {}", commentDto.toString());

        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

        log.info("Post retrieved from db with {}", post.toString());

        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        log.info("response from commentRepository with {}", newComment.toString());

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {

        Comment comment = getComment(postId, commentId);

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentDto) {


        Comment comment = getComment(postId, commentId);

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        Comment updatedComment = commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {

        Comment comment = getComment(postId, commentId);
        commentRepository.delete(comment);
    }

    private Comment getComment(Long postId, Long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", String.valueOf(postId))
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "id", commentId.toString())
        );

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return comment;
    }

    private Comment mapToEntity(CommentDto commentDto){
        return mapper.map(commentDto, Comment.class);
        /*return new Comment.CommentBuilder()
                .setName(commentDto.getName())
                .setEmail(commentDto.getEmail())
                .setBody(commentDto.getBody())
                .build();*/
    }

    private CommentDto mapToDto(Comment comment){
        return mapper.map(comment, CommentDto.class);
        /*return new CommentDto.CommentDtoBuilder()
                .setId(comment.getId())
                .setName(comment.getName())
                .setEmail(comment.getEmail())
                .setBody(comment.getBody())
                .build();*/
    }
}
