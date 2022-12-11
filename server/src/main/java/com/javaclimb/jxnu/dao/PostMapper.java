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


    List<Map<String,Object>> selectPostAllMessage();


    int selectsumpostmonth1(int author_id);
    int selectsumpostmonth2(int author_id);
    int selectsumpostmonth3(int author_id);
    int selectsumpostmonth4(int author_id);
    int selectsumpostmonth5(int author_id);
    int selectsumpostmonth6(int author_id);
    int selectsumpostmonth7(int author_id);
    int selectsumpostmonth8(int author_id);
    int selectsumpostmonth9(int author_id);
    int selectsumpostmonth10(int author_id);
    int selectsumpostmonth11(int author_id);
    int selectsumpostmonth12(int author_id);

    int selectsuminterlocutionmonth1(int author_id);
    int selectsuminterlocutionmonth2(int author_id);
    int selectsuminterlocutionmonth3(int author_id);
    int selectsuminterlocutionmonth4(int author_id);
    int selectsuminterlocutionmonth5(int author_id);
    int selectsuminterlocutionmonth6(int author_id);
    int selectsuminterlocutionmonth7(int author_id);
    int selectsuminterlocutionmonth8(int author_id);
    int selectsuminterlocutionmonth9(int author_id);
    int selectsuminterlocutionmonth10(int author_id);
    int selectsuminterlocutionmonth11(int author_id);
    int selectsuminterlocutionmonth12(int author_id);

    int selectsumcommentsmonth1(int author_id);
    int selectsumcommentsmonth2(int author_id);
    int selectsumcommentsmonth3(int author_id);
    int selectsumcommentsmonth4(int author_id);
    int selectsumcommentsmonth5(int author_id);
    int selectsumcommentsmonth6(int author_id);
    int selectsumcommentsmonth7(int author_id);
    int selectsumcommentsmonth8(int author_id);
    int selectsumcommentsmonth9(int author_id);
    int selectsumcommentsmonth10(int author_id);
    int selectsumcommentsmonth11(int author_id);
    int selectsumcommentsmonth12(int author_id);

    int selectsumcommentsnextmonth1(int author_id);
    int selectsumcommentsnextmonth2(int author_id);
    int selectsumcommentsnextmonth3(int author_id);
    int selectsumcommentsnextmonth4(int author_id);
    int selectsumcommentsnextmonth5(int author_id);
    int selectsumcommentsnextmonth6(int author_id);
    int selectsumcommentsnextmonth7(int author_id);
    int selectsumcommentsnextmonth8(int author_id);
    int selectsumcommentsnextmonth9(int author_id);
    int selectsumcommentsnextmonth10(int author_id);
    int selectsumcommentsnextmonth11(int author_id);
    int selectsumcommentsnextmonth12(int author_id);

    int selectsumpostmonth123(int author_id,String startmonth,String endmonth);
    void deletecontent(int id);
}
