package com.jloroz.blogrestapi.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PostResponse(PostResponseBuilder builder){
        this.content = builder.content;
        this.pageNo = builder.pageNo;
        this.pageSize = builder.pageSize;
        this.totalElements = builder.totalElements;
        this.totalPages = builder.totalPages;
        this.last = builder.last;
    }

    public static class PostResponseBuilder{
        private List<PostDto> content;
        private int pageNo;
        private int pageSize;
        private long totalElements;
        private int totalPages;
        private boolean last;

        public PostResponseBuilder setContent(List<PostDto> posts){
            this.content = posts;
            return this;
        }
        public PostResponseBuilder setPageNo(int pageNo){
            this.pageNo = pageNo;
            return this;
        }
        public PostResponseBuilder setPageSize(int pageSize){
            this.pageSize = pageSize;
            return this;
        }
        public PostResponseBuilder setTotalElements(long totalElements){
            this.totalElements = totalElements;
            return this;
        }
        public PostResponseBuilder setTotalPages(int totalPages){
            this.totalPages = totalPages;
            return this;
        }
        public PostResponseBuilder setLast(boolean last){
            this.last = last;
            return this;
        }
        public PostResponse build(){
            return new PostResponse(this);
        }
    }
}
