package com.example.dietAssistant.dto;

import lombok.Data;

@Data
public class PostLike {

    private Integer likeId;

    private Integer userId;

    private Integer postId;

    private Long likeTime;

}
