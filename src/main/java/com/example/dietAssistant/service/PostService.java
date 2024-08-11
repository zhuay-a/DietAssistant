package com.example.dietAssistant.service;

import com.example.dietAssistant.dto.Post;
import com.example.dietAssistant.dto.PostLike;

import java.util.List;

public interface PostService {
    void addPost(Post post);

    List<Post> getPost(Integer userId);

    void modifyPost(Post post);

    void likePost(PostLike postLike);

    void deletePostLike(PostLike postLike);
}
