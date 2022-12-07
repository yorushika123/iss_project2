package com.javaclimb.jxnu.dao;

import com.javaclimb.jxnu.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {
    Post findPostByAuthor(String author_name);
    Post findPostByTitle(String post_title);
    Post findPostByAuthor(int author_id);
    void addPost(String post_title,int author_id,int up_counts,String img,int comment_counts,int view_counts);
    void deletePostByID(int id);
    void deletePostByTitle(String post_title);
    List<Post> findAllPost();
    List<Post> findPostByTitleL(String post_title);

    Contents findContentID(int id);


    List<Post> findPostByAuthorL(String author_name);


    User findAuthorID(int post_id);
    int selectSumPost(int author_id);
    int selectSumView(int author_id);
    int selectSumGood(int author_id);


//    int selectSumPostWeek(int author_id);
    int selectSumComment(int author_id);

    List<Map<String,Object>> select(String post_title, String author_name,String sort);
    List<Map<Integer,Object>> selectSum(int author_id);
    List<Map<Integer,Object>> selectSumByTime(int author_id,String startTime,String endTime);

    void addReply(int comments_id,String contents,int user_id);
    List<Comments> selectAllComments(int post_id);
    User findUserByID(int id);
    List<Comments_next> selectAllComments_next(int comments_id);
    Post selectPostByID(int post_id);
    void UpdateGood(int id,int up_counts);
}
