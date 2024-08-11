package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.Post;
import com.example.dietAssistant.dto.PostLike;
import com.example.dietAssistant.mapper.PostMapper;
import com.example.dietAssistant.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    @Override
    public void addPost(Post post) {
        postMapper.addPost(post);
    }

    @Override
    public List<Post> getPost(Integer userId) {
        return postMapper.getPost(userId);
    }

    @Override
    public void modifyPost(Post post) {
        postMapper.modifyPost(post);
    }

    @Override
    @Transactional
    public void likePost(PostLike postLike) {
        postMapper.likePost(postLike);
        postMapper.addLike(postLike);
    }

    @Override
    public void deletePostLike(PostLike postLike) {
        postMapper.deletePostLike(postLike);
        postMapper.decPostLike(postLike);
    }
}
