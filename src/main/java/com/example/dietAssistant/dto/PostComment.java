package com.example.dietAssistant.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostComment {
    private Integer commentId;

    private Integer postId;

    private Integer userId;

    private String commentContent;

    private Integer likeCount;

    private LocalDateTime commentTime;

    private Integer replyTo;
}
