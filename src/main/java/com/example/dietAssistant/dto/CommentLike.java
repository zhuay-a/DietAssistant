package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class CommentLike {
    private Integer likeId;

    private Integer userId;

    private Integer commentId;

    private Long likeTime;
}
