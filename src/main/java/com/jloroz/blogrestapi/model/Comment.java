package com.jloroz.blogrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    private Post post;

    public Comment(CommentBuilder commentBuilder){
        this.id = commentBuilder.id;
        this.name = commentBuilder.name;
        this.email = commentBuilder.email;
        this.body = commentBuilder.body;
    }
    public static class CommentBuilder {
        private Long id;
        private String name;
        private String email;
        private String body;


        public CommentBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public CommentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public CommentBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public CommentBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }
}
