package com.example.demo.jpa;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
