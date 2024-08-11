package com.example.dietAssistant.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {
    private Integer postId;

    private Integer userId;

    private Integer likeCount;

    private String content;

    private LocalDateTime postTime;

    private LocalDateTime lastModifiedTime;
}
