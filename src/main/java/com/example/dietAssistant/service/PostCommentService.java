package com.example.dietAssistant.service;

import com.example.dietAssistant.dto.CommentLike;
import com.example.dietAssistant.dto.PostComment;

public interface PostCommentService {
    void addComment(PostComment postComment);

    void likeComment(CommentLike commentLike);

    void deleteCommentLike(CommentLike commentLike);
}
