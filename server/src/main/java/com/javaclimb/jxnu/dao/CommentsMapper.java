package com.javaclimb.jxnu.dao;

import com.javaclimb.jxnu.domain.Comments;
import com.javaclimb.jxnu.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentsMapper {

    List<Comments>  findAllComments();
    List<Comments> findCommentsByPostIDL(int post_id);
    List<Comments> findCommentsByUserIDL(int user_id);
    Comments findCommentsByPostID(int post_id);
    Comments findCommentsByUserID(int user_id);
    void deleteComments(int post_id);
    void addComments(int post_id,int user_id,String comments_content);
}
