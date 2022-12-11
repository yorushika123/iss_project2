package com.javaclimb.jxnu.controller;

import com.javaclimb.jxnu.dao.CommentsMapper;
import com.javaclimb.jxnu.dao.PostMapper;
import com.javaclimb.jxnu.domain.Comments;
import com.javaclimb.jxnu.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/Comments")
public class CommentsController {
    @Autowired
    CommentsMapper commentsMapper;

    @RequestMapping("/deleteComments")
    public Result deleteComments(HttpServletRequest request){
        int post_id= Integer.parseInt(request.getParameter("post_id"));
        commentsMapper.deleteComments(post_id);
        return  new Result(Code.SUCCESS,"删除成功");
    }

    @RequestMapping("/findCommentsByPostID")
    public Result findCommentsByPostID(HttpServletRequest request){
       int post_id=Integer.parseInt(request.getParameter("post_id"));
        Comments comments=commentsMapper.findCommentsByPostID(post_id);
        if (comments != null) {
            return new Result(Code.SUCCESS, "查找成功",comments);
        }
        return new Result(Code.FAIL,"查找失败");
    }

    @RequestMapping("/findCommentsByPostIDL")
    public Result findCommentsByPostIDL(HttpServletRequest request){
        int post_id=Integer.parseInt(request.getParameter("post_id"));
       List<Comments> comments =commentsMapper.findCommentsByPostIDL(post_id);
        if (!comments.isEmpty()){
            return new Result(Code.SUCCESS, "查找成功",comments);
        }
        return new Result(Code.FAIL,"查找失败");
    }

    @RequestMapping("/findCommentsByUserID")
    public Result findCommentsByUserID(HttpServletRequest request){
        int user_id=Integer.parseInt(request.getParameter("user_id"));
        Comments comments=commentsMapper.findCommentsByUserID(user_id);
        if (comments != null) {
            return new Result(Code.SUCCESS, "查找成功",comments);
        }
        return new Result(Code.FAIL,"查找失败");
    }

    @RequestMapping("/findCommentsByUserIDL")
    public Result findCommentsByUserIDL(HttpServletRequest request){
        int user_id=Integer.parseInt(request.getParameter("user_id"));
        List<Comments> comments=commentsMapper.findCommentsByUserIDL(user_id);
        if (!comments.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功",comments);
        }
        return new Result(Code.FAIL,"查找失败");
    }

    @RequestMapping("/findAllComments")
    public Result findAllComments()
    {
        List<Comments> comments = commentsMapper.findAllComments();
        return new Result(Code.SUCCESS,"查找成功",comments);
    }






    @RequestMapping("/selectSumCommentsBYTime")
    public Result selectSumCommentsBYTime(HttpServletRequest request) {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        int x=commentsMapper.selectSumCommentsBYTime(user_id,startTime,endTime);
        int y=commentsMapper.selectSumComments_nextBYTime(user_id,startTime,endTime);
        return new Result(Code.SUCCESS, "查找成功", x+y);
    }


}
