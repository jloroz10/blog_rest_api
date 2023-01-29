package com.jloroz.blogrestapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.Set;

@Getter
@Setter
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

    @ToString.Exclude
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    public Post(PostBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description =builder. description;
        this.content = builder.content;
        this.comments = builder.comments;
    }

    public static class PostBuilder{
        private Long id;
        private String title;
        private String description;
        private String content;
        private Set<Comment> comments;

        public PostBuilder setId(Long id){
            this.id = id;
            return this;
        }

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

        public PostBuilder setComments(Set<Comment> comments){
            this.comments = comments;
            return this;
        }

        public Post build(){
            return new Post(this);
        }
    }
}
