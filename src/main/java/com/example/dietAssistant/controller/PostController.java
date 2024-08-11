package com.example.dietAssistant.controller;

import com.example.dietAssistant.dto.Post;
import com.example.dietAssistant.dto.PostLike;
import com.example.dietAssistant.result.Result;
import com.example.dietAssistant.service.PostService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/addpost")
    @ResponseBody
    public Result<String> addPost(@RequestBody Post post) {
        postService.addPost(post);
        return Result.success("发布动态成功");
    }

    @GetMapping("/getpost")
    @ResponseBody
    public Result<List<Post>> getPost(@RequestParam Integer userId) {
        List<Post> list = postService.getPost(userId);
        Result result = Result.success("查询动态成功").data(list);
        return result;
    }

    @PutMapping("/modifyPost")
    @ResponseBody
    public Result<String> modifyPost(@RequestBody Post post) {
        postService.modifyPost(post);
        return Result.success("修改动态成功");
    }

    @PostMapping("/likePost")
    @ResponseBody
    public Result<String> likePost(@RequestBody PostLike postLike) {
        postService.likePost(postLike);
        return Result.success("点赞动态成功");
    }

    @DeleteMapping("/deletePostLike")
    @ResponseBody
    public Result<String> deletePostLike(@RequestBody PostLike postLike) {
        postService.deletePostLike(postLike);
        return Result.success("取消点赞动态成功");
    }
}
