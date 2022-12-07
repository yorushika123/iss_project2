package com.javaclimb.jxnu.controller;
import com.javaclimb.jxnu.dao.ContentsMapper;
import com.javaclimb.jxnu.domain.Contents;
import com.javaclimb.jxnu.domain.Post;
import com.javaclimb.jxnu.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
public class ContentsController {
    @Autowired
    ContentsMapper contentsMapper;
@RequestMapping(value = "/user/write/article",method = RequestMethod.POST)
    public Result saveArticle( @RequestBody Contents contents){

 /*   Date date =new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
    String date1 = sdf.format(date);
    System.out.println(date);
    contents.setArticleDate(date);*/
  /*  int contents_id =contentsMapper.selectID(contents.getArticleTitle(), contents.getArticleContentHtml(), contents.getArticleContentMd());
    System.out.println(contents_id);*/
    Contents contents2 =contentsMapper.selectID(contents.getArticleTitle(), contents.getArticleContentHtml(), contents.getArticleContentMd());
    if(contents2==null){
    contentsMapper.addOrUpdate(contents.getArticleTitle(), contents.getArticleContentHtml(), contents.getArticleAbstract(), contents.getArticleContentMd(),contents.getArticleCover(),contents.getCreate_time());
        Contents contents3 =contentsMapper.selectID(contents.getArticleTitle(), contents.getArticleContentHtml(), contents.getArticleContentMd());
        int contents_id=contents3.getId();
        HashMap<String, Integer> map = new HashMap<>();
       map.put("id", contents_id);
       return new Result(Code.SUCCESS,"保存成功",map);

}
    return new Result(Code.FAIL,"保存失败");
}


    @CrossOrigin
    @RequestMapping(value ="/user/write/covers",method = RequestMethod.POST)
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "D:/workspace/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8888/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    @RequestMapping(value = "/user/write/addPost",method = RequestMethod.POST)
    public Result saveArticle( @RequestBody Post post) {
        contentsMapper.addPost(post.getPost_title(), post.getImg(), post.getAuthor_id(), post.getContents_id());
        if(post.getAuthor_id() ==0){

            return new Result(Code.FAIL,"保存失败");
        }
        return new Result(Code.SUCCESS,"保存成功");


    }

}
