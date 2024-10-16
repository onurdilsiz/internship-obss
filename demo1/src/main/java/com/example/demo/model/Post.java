package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post extends BaseEntity{
    private String title;
    private String content;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "post")
    private List<PostComment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private File image;

    public void addComment(PostComment comment){
        comments.add(comment);
        comment.setPost(this);
    }

}
