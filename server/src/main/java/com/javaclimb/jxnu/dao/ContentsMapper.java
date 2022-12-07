package com.javaclimb.jxnu.dao;

import com.javaclimb.jxnu.domain.Contents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

@Mapper
public interface ContentsMapper {
    Contents findById( @Param("id")int id);
    void addOrUpdate(@Param("articleTitle")String articleTitle, @Param("articleContentHtml")String articleContentHtml, @Param("articleAbstract")String articleAbstract, @Param("articleContentMd")String articleContentMd, @Param("articleCover")String articleCover, @Param("create_time") Date create_time);
   void addPost(String post_title,String img,int author_id,int content_id);
    Contents selectID(@Param("articleTitle")String articleTitle, @Param("articleContentHtml")String articleContentHtml,  @Param("articleContentMd")String articleContentMd);
}
