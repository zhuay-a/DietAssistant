package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.CommentLike;
import com.example.dietAssistant.dto.PostComment;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/postcomment")
public class PostCommentsController {

    @Autowired
    PostCommentService postCommentService;

    @PostMapping("/addComment")
    @ResponseBody
    public Result<String> addComment(@RequestBody PostComment postComment) {
        postCommentService.addComment(postComment);
        return Result.success("用户评论成功");
    }

    @PostMapping("/likeComment")
    @ResponseBody
    public Result<String> likeComment(@RequestBody CommentLike commentLike) {
        postCommentService.likeComment(commentLike);
        return Result.success("点赞评论成功");
    }

    @DeleteMapping("/deleteCommentLike")
    @ResponseBody
    public Result<String> deleteCommentLike(@RequestBody CommentLike commentLike) {
        postCommentService.deleteCommentLike(commentLike);
        return Result.success("取消评论点赞成功");
    }
}
