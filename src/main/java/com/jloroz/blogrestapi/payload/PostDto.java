package com.jloroz.blogrestapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String content;

    public static class PostDtoBuilder{
        private Long id;
        private String title;
        private String description;
        private String content;

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

        public PostDto build(){

            return new PostDto(id, title, description, content);
        }
    }
}
