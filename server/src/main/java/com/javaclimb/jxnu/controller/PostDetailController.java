package com.javaclimb.jxnu.controller;

import com.javaclimb.jxnu.dao.CommentsMapper;
import com.javaclimb.jxnu.dao.PostMapper;
import com.javaclimb.jxnu.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@RestController
public class PostDetailController {
    @Autowired
    PostMapper postMapper;
    @Autowired
    CommentsMapper commentsMapper;
    @RequestMapping(value = "//postDetail/selectByid",method = RequestMethod.POST)
    public Result SelectPostContentByID(@RequestBody Post post){

        int post_id= post.getId();
 Post post1 = postMapper.selectPostByID(post_id);
        Contents contents=postMapper.findContentID(post_id);
        contents.setCreate_time(post1.getCreate_time());
        System.out.println(contents.getCreate_time());
        if(contents ==null){
            return new Result(Code.FAIL,"保存失败");
        }
        return new Result(Code.SUCCESS,"保存成功",contents);

    }
    @RequestMapping(value = "//postDetail/addComment",method = RequestMethod.POST)
    public Result addComment(@RequestBody Comments comments){
        System.out.println(comments);
         int post_id=comments.getPost_id();
         String comments_content= comments.getComments_content();
         int user_id=comments.getUser_id();
        System.out.println(comments_content);
        commentsMapper.addComments( post_id, user_id,comments_content);
        if(comments_content ==null){
            return new Result(Code.FAIL,"保存失败");
        }
        return new Result(Code.SUCCESS,"保存成功");

    }
    @RequestMapping(value = "/postDetail/selectAll",method = RequestMethod.POST)
    public Result selectAll( @RequestBody User user){
      /*  System.out.println(user);*/
        int SumPost=postMapper.selectSumPost(user.getId());
        int SumView=postMapper.selectSumView(user.getId());
        int SumGood=postMapper.selectSumGood(user.getId());
        HashMap<String, Integer> map = new HashMap<>();
        map.put("SumPost", SumPost);
        map.put("SumView", SumView);
        map.put("SumGood", SumGood);
        return new Result(Code.SUCCESS,"保存成功",map);
    }
    @RequestMapping(value = "//postDetail/selectAuthorByid",method = RequestMethod.POST)
    public Result SelectAuthorByID(@RequestBody Post post){
        int post_id= post.getId();
        User user=postMapper.findAuthorID(post_id);
        if(user ==null){
            return new Result(Code.FAIL,"保存失败");
        }

        return new Result(Code.SUCCESS,"保存成功",user);

    }

    @RequestMapping(value = "//postDetail/replyComments",method = RequestMethod.POST)
    public Result SelectAuthorByID(@RequestBody Comments_next reply){
/*        System.out.println(reply);*/
    postMapper.addReply(reply.getComments_id(), reply.getContents(),reply.getUser_id());
        if(reply==null){
            return new Result(Code.FAIL,"回复失败");
        }
        return new Result(Code.SUCCESS,"成功");
    }

    @RequestMapping(value = "//postDetail/selectAllComments",method = RequestMethod.POST)
    public Result selectAllComments(@RequestBody Post post){
        List<Comments> comments=postMapper.selectAllComments(post.getId());
/*        System.out.println(comments);*/


        for(Comments i : comments){
            User  user1=postMapper.findUserByID(i.getUser_id());
            i.setUsername(user1.getUsername());
            i.setAvatar(user1.getAvatar());
            List<Comments_next> comments_nexts = postMapper.selectAllComments_next(i.getId());
            for(Comments_next j :comments_nexts) {
                User user2 = postMapper.findUserByID(j.getUser_id());
                j.setAvatar(user2.getAvatar());
                j.setUsername(user2.getUsername());
                /*System.out.println(j.getUsername());*/
            }
                 /*System.out.println(comments_nexts);*/
                 i.setComments_next(comments_nexts);
           /* System.out.println(i.getComments_content());
                 System.out.println(i.getCreate_time());*/
        }

        if(comments==null){
            return new Result(Code.FAIL,"查找失败");
        }
        return new Result(Code.SUCCESS,"查找成功",comments);
    }

@RequestMapping(value = "//postDetail/Good",method = RequestMethod.POST)
public Result Good( @RequestBody Post post){
    System.out.println(post.getId());
    Post post2 =postMapper.selectPostByID(post.getId());
  int sum =  post2.getUp_counts()+post.getUp_counts();
  postMapper.UpdateGood(post.getId(),sum);
    return new Result(Code.SUCCESS,"点赞成功");
}

}
