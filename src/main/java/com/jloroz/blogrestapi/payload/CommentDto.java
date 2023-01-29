package com.jloroz.blogrestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private String name;
    private String email;
    private String body;

    public CommentDto(CommentDtoBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.body = builder.body;
    }

    public static class CommentDtoBuilder{
        private Long id;
        private String name;
        private String email;
        private String body;

        public CommentDtoBuilder setId(Long id){
            this.id = id;
            return this;
        }
        public CommentDtoBuilder setName(String name){
            this.name = name;
            return this;
        }
        public CommentDtoBuilder setEmail(String email){
            this.email = email;
            return this;
        }
        public CommentDtoBuilder setBody(String body){
            this.body = body;
            return this;
        }
        public CommentDto build(){
            return new CommentDto(this);
        }
    }
}
