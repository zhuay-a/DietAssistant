package com.example.dietAssistant.service.impl;

import com.example.dietAssistant.dto.CommentLike;
import com.example.dietAssistant.dto.PostComment;
import com.example.dietAssistant.mapper.PostCommentMapper;
import com.example.dietAssistant.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCommentServiceImpl implements PostCommentService {

    @Autowired
    PostCommentMapper postCommentMapper;

    @Override
    public void addComment(PostComment postComment) {
        postCommentMapper.addComment(postComment);
    }

    @Override
    public void likeComment(CommentLike commentLike) {
        postCommentMapper.incLike(commentLike);
        postCommentMapper.addCommentLike(commentLike);
    }

    @Override
    public void deleteCommentLike(CommentLike commentLike) {
        postCommentMapper.decCommentLike(commentLike);
        postCommentMapper.deleteCommentLike(commentLike);
    }
}
