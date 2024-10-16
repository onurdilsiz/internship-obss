package com.example.demo.repository;

import com.example.demo.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostCommentRepository extends JpaRepository<PostComment, UUID> {
}
