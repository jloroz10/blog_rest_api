package com.jloroz.blogrestapi.payload;

import com.jloroz.blogrestapi.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;

    public PostDto(PostDtoBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.content = builder.content;
        this.comments = builder.comments;
    }

    public static class PostDtoBuilder{
        private Long id;
        private String title;
        private String description;
        private String content;
        private Set<CommentDto> comments;

        public PostDtoBuilder setId(Long id){
            this.id=id;
            return this;
        }
        public PostDtoBuilder setTitle(String title){
            this.title=title;
            return this;
        }
        public PostDtoBuilder setDescription(String description){
            this.description=description;
            return this;
        }
        public PostDtoBuilder setContent(String content){
            this.content=content;
            return this;
        }
        public PostDtoBuilder setComments(Set<CommentDto> comments){
            this.comments=comments;
            return this;
        }
        public PostDto build(){
            return new PostDto(this);
        }
    }
}
