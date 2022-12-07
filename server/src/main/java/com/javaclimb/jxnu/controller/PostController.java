package com.javaclimb.jxnu.controller;

import com.javaclimb.jxnu.dao.PostMapper;
import com.javaclimb.jxnu.domain.Admin;
import com.javaclimb.jxnu.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Post")
public class PostController {

    @Autowired
    PostMapper postMapper;


    @RequestMapping("/deletePostByID")
    public Result deletePostByID(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        postMapper.deletePostByID(id);
        return new Result(Code.SUCCESS, "删除成功");
    }


    @RequestMapping("/deletePostByTitle")
    public Result deletePostByTitle(HttpServletRequest request) {
        String post_title = request.getParameter("post_title");
        postMapper.deletePostByTitle(post_title);
        return new Result(Code.SUCCESS, "删除成功");
    }


    @RequestMapping("/findPostByTitle")
    public Result findPostByTitle(HttpServletRequest request) {
        String post_title = request.getParameter("post_title");
        Post post = postMapper.findPostByTitle(post_title);
        if (post != null) {
            return new Result(Code.SUCCESS, "查找成功", post);
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping("/findPostByTitleL")
    public Result findPostByTitleL(HttpServletRequest request) {
        String post_title = request.getParameter("post_title");
        List<Post> post = postMapper.findPostByTitleL(post_title);
        if (!post.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功", post);
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping("/findPostByAuthor")
    public Result findPostByAuthor(HttpServletRequest request) {
        String author_name = request.getParameter("author_name");
        Post post = postMapper.findPostByAuthor(author_name);
        if (post != null) {
            return new Result(Code.SUCCESS, "查找成功", post);
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping("/findPostByAuthorL")
    public Result findPostByAuthorL(HttpServletRequest request) {
        String author_name = request.getParameter("author_name");
        List<Post> post = postMapper.findPostByAuthorL(author_name);
        if (!post.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功", post);
        }
        return new Result(Code.FAIL, "查找失败");
    }


    @RequestMapping("/findAllPost")
    public Result findAllPost() {
        List<Post> post = postMapper.findAllPost();
        return new Result(Code.SUCCESS, "查找成功", post);
    }

    @RequestMapping("/select")
    public Result select(HttpServletRequest request) {
        String author_name = request.getParameter("author_name");
        String post_title = request.getParameter("post_title");
        String sort = request.getParameter("sort");
        List<Map<String, Object>> post = postMapper.select(post_title, author_name, sort);
        if (!post.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功", post);
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping(value = "/addPost")
    public Result addPost(HttpServletRequest request) {
        String post_title = request.getParameter("post_title");
        int author_id = Integer.parseInt(request.getParameter("author_id"));
        int up_counts = Integer.parseInt(request.getParameter("up_counts"));
        String img = request.getParameter("img");
        int comment_counts = Integer.parseInt(request.getParameter("comment_counts"));
        int view_counts = Integer.parseInt(request.getParameter("view_counts"));
        Post post = postMapper.findPostByTitle(post_title);
        if (post == null) {
            postMapper.addPost(post_title, author_id, up_counts, img, comment_counts, view_counts);
            return new Result(Code.SUCCESS, "发布成功");
        }
        return new Result(Code.FAIL, "发布失败");
    }




    @RequestMapping("/selectSumPost/{author_id}")
    public Result selectSumPost(@PathVariable int author_id) {
        int x = postMapper.selectSumPost(author_id);
        return new Result(Code.SUCCESS, "查找成功", x);
    }

    @RequestMapping("/selectSumView/{author_id}")
    public Result selectSumView(@PathVariable int author_id) {
        int x = postMapper.selectSumView(author_id);
        return new Result(Code.SUCCESS, "查找成功", x);
    }

    @RequestMapping("/selectSumGood/{author_id}")
    public Result selectSumGood(@PathVariable int author_id) {
        int x = postMapper.selectSumGood(author_id);
        return new Result(Code.SUCCESS, "查找成功", x);
    }

    @RequestMapping("/selectSumComment/{author_id}")
    public Result selectSumComment(@PathVariable int author_id) {
        int x = postMapper.selectSumComment(author_id);
        return new Result(Code.SUCCESS, "查找成功", x);
    }

    @RequestMapping("/selectSum/{author_id}")
    public Result selectSum(@PathVariable int author_id) {
        List<Map<Integer, Object>> x = postMapper.selectSum(author_id);
        return new Result(Code.SUCCESS, "查找成功", x.get(0));
    }

//    @RequestMapping("/selectSumPostWeek/{author_id}")
//    public Result selectSumPostWeek(@PathVariable int author_id) {
//        int x = postMapper.selectSumPostWeek(author_id);
//        return new Result(Code.SUCCESS, "查找成功", x);
//    }

    @RequestMapping("/selectSumBYtime")
    public Result selectSumBYtime(HttpServletRequest request) {
        int author_id = Integer.parseInt(request.getParameter("author_id"));
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        List<Map<Integer, Object>> a = postMapper.selectSumByTime(author_id, startTime, endTime);
        if (!a.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功", a.get(0));
        }
        return new Result(Code.FAIL, "发布失败");
    }
}