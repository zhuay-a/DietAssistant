package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.CommentLike;
import com.example.dietAssistant.dto.PostComment;
import com.example.dietAssistant.mapper.PostCommentMapper;
import com.example.dietAssistant.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

@Service
public class PostCommentServiceImpl implements PostCommentService {

    @Autowired
    PostCommentMapper postCommentMapper;

    @Autowired
    ThreadPoolExecutor commentThreadPool;

    @Override
    public void addComment(PostComment postComment) {
        //发布评论
//        postCommentMapper.addComment(postComment);
        commentThreadPool.execute(() -> {
            postCommentMapper.addComment(postComment);
        });
    }

    @Override
    public void likeComment(CommentLike commentLike) {
        //点赞评论
//        postCommentMapper.incLike(commentLike);
//        postCommentMapper.addCommentLike(commentLike);
        commentThreadPool.execute(() -> {
            postCommentMapper.incLike(commentLike);
            postCommentMapper.addCommentLike(commentLike);
        });
    }

    @Override
    public void deleteCommentLike(CommentLike commentLike) {
        //取消点赞评论
//        postCommentMapper.decCommentLike(commentLike);
//        postCommentMapper.deleteCommentLike(commentLike);
        commentThreadPool.execute(() -> {
            postCommentMapper.decCommentLike(commentLike);
            postCommentMapper.deleteCommentLike(commentLike);
        });
    }
}
