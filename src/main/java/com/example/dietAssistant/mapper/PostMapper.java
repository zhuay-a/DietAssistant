package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.Post;
import com.example.dietAssistant.dto.PostLike;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    void addPost(Post post);

    @Select("select * from posts where user_id = #{userId}")
    List<Post> getPost(Integer userId);

    void modifyPost(Post post);

    @Insert("insert into postlikes(post_id, user_id, like_time) VALUES (#{postId}, #{userId}, #{likeTime})")
    void likePost(PostLike postLike);

    @Update("update posts set like_count = like_count + 1 where post_id = #{postId}")
    void addLike(PostLike postLike);

    @Delete("delete from postlikes where like_id = #{likeId}")
    void deletePostLike(PostLike postLike);

    @Update("update posts set like_count = like_count - 1 where post_id = #{postId}")
    void decPostLike(PostLike postLike);
}
