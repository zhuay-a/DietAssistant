package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.Post;
import com.example.dietAssistant.dto.PostLike;
import com.example.dietAssistant.mapper.PostMapper;
import com.example.dietAssistant.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;

    @Autowired
    ThreadPoolExecutor postThreadPool;

    @Override
    public void addPost(Post post) {
        //发布动态
        postThreadPool.execute(() -> {
            postMapper.addPost(post);
        });
//        postMapper.addPost(post);
    }

    @Override
    public List<Post> getPost(Integer userId) {
        //查询动态
        return postMapper.getPost(userId);
    }

    @Override
    public void modifyPost(Post post) {
        //修改动态
//        postMapper.modifyPost(post);
        postThreadPool.execute(() -> {
            postMapper.modifyPost(post);
        });
    }

    @Override
    @Transactional
    public void likePost(PostLike postLike) {
        //点赞动态
//        postMapper.likePost(postLike);
//        postMapper.addLike(postLike);
        postThreadPool.execute(() -> {
            postMapper.likePost(postLike);
            postMapper.addLike(postLike);
        });
    }

    @Override
    public void deletePostLike(PostLike postLike) {
        //取消点赞动态
//        postMapper.deletePostLike(postLike);
//        postMapper.decPostLike(postLike);
        postThreadPool.execute(() -> {
            postMapper.deletePostLike(postLike);
            postMapper.decPostLike(postLike);
        });
    }
}
