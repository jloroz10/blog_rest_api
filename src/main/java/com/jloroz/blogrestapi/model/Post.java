package com.jloroz.blogrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(
        name="posts",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="content", nullable = false)
    private String content;

    public Post(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public static class PostBuilder{
        private String title;
        private String description;
        private String content;

        public PostBuilder setTitle(String title){
            this.title = title;
            return this;
        }

        public PostBuilder setDescription(String description){
            this.description = description;
            return this;
        }

        public PostBuilder setContent(String content){
            this.content = content;
            return this;
        }

        public Post build(){
            return new Post(this.title,this.description, this.content);
        }
    }
}
