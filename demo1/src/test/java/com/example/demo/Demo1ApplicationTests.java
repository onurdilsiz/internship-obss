package com.example.demo;

import com.example.demo.model.Post;
import com.example.demo.model.PostComment;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.demo.common.Constants.Roles.ROLE_ADMIN;

@SpringBootTest
class Demo1ApplicationTests {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;

    @Test
    void contextLoads() {
        Post post = new Post();
        post.setTitle("Test Title");
        post.setContent("Test Content");

        post =postRepository.save(post);

        PostComment postComment = new PostComment();
        postComment.setComment("Test Comment");

        post.addComment(postComment);
        System.out.println("comment eklendi");
        postRepository.save(post);
        System.out.println("post save oldu");

        post.getComments().stream().forEach(System.out::println);

        User user = new User();
        user.setName("admin");
        user.setSurname("admin");
        user.setEmail("adsdsaf@asdf.co");
        user.setUsername("admin");
        user.setEnabled(Boolean.TRUE);

        userRepository.save(user);


        Role adminRole = roleService.findByName(ROLE_ADMIN);
        user.addRole(adminRole);


        userRepository.findUserById(user.getId());

        postRepository.findPostById(post.getId()).orElse(null).getComments().stream().forEach(System.out::println);

        Assertions.assertTrue(true);
    }

}
