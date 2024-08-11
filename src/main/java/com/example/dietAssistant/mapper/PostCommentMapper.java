package com.example.dietAssistant.mapper;

import com.example.dietAssistant.dto.CommentLike;
import com.example.dietAssistant.dto.PostComment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PostCommentMapper {
    void addComment(PostComment postComment);

    @Update("update postcomments set like_count = like_count + 1 where comment_id = #{commentId}")
    void incLike(CommentLike commentLike);

    @Insert("insert into commentlikes(like_id, commit_id, user_id, like_time) VALUES (#{likeId}, #{commentId}, #{userId}, #{likeTime})")
    void addCommentLike(CommentLike commentLike);

    @Update("update postcomments set like_count = like_count - 1 where comment_id = #{commentId}")
    void decCommentLike(CommentLike commentLike);

    @Delete("delete from commentlikes where like_id = #{likeId}")
    void deleteCommentLike(CommentLike commentLike);
}
