package com.javaclimb.jxnu.controller;

import com.javaclimb.jxnu.dao.PostMapper;
import com.javaclimb.jxnu.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
@RequestMapping("/Post")
public class PostController {

    @Autowired
    PostMapper postMapper;




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




    @RequestMapping("/selectPostAllMessage")
    public Result selectPostAllMessage() {
        List<Map<String,Object>> a = postMapper.selectPostAllMessage();
        if (!a.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功", a);
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping("/selectsumpostmonth1/{author_id}")
    public Result selectsumpostmonth1(@PathVariable int author_id,HttpServletRequest request) {
        int startmonth =Integer.parseInt(request.getParameter("startmonth"));
        int endmonth = Integer.parseInt(request.getParameter("endmonth"));
//        int[] x=new int[12];
//        for(int i=startmonth;i<endmonth;i++)
//        {
//          /*  x[i-1]=postMapper.selectsumpostmonth123(author_id,startmonth,startmonth+1);*/
//        }
        int x1=postMapper.selectsumpostmonth1(author_id);
        int x2=postMapper.selectsumpostmonth2(author_id);
        int x3=postMapper.selectsumpostmonth3(author_id);
        int x4=postMapper.selectsumpostmonth4(author_id);
        int x5=postMapper.selectsumpostmonth5(author_id);
        int x6=postMapper.selectsumpostmonth6(author_id);
        int x7=postMapper.selectsumpostmonth7(author_id);
        int x8=postMapper.selectsumpostmonth8(author_id);
        int x9=postMapper.selectsumpostmonth9(author_id);
        int x10=postMapper.selectsumpostmonth10(author_id);
        int x11=postMapper.selectsumpostmonth11(author_id);
        int x12=postMapper.selectsumpostmonth12(author_id);

        int z1=postMapper.selectsuminterlocutionmonth1(author_id);
        int z2=postMapper.selectsuminterlocutionmonth2(author_id);
        int z3=postMapper.selectsuminterlocutionmonth3(author_id);
        int z4=postMapper.selectsuminterlocutionmonth4(author_id);
        int z5=postMapper.selectsuminterlocutionmonth5(author_id);
        int z6=postMapper.selectsuminterlocutionmonth6(author_id);
        int z7=postMapper.selectsuminterlocutionmonth7(author_id);
        int z8=postMapper.selectsuminterlocutionmonth8(author_id);
        int z9=postMapper.selectsuminterlocutionmonth9(author_id);
        int z10=postMapper.selectsuminterlocutionmonth10(author_id);
        int z11=postMapper.selectsuminterlocutionmonth11(author_id);
        int z12=postMapper.selectsuminterlocutionmonth12(author_id);

        int m1=postMapper.selectsumcommentsmonth1(author_id);
        int m2=postMapper.selectsumcommentsmonth2(author_id);
        int m3=postMapper.selectsumcommentsmonth3(author_id);
        int m4=postMapper.selectsumcommentsmonth4(author_id);
        int m5=postMapper.selectsumcommentsmonth5(author_id);
        int m6=postMapper.selectsumcommentsmonth6(author_id);
        int m7=postMapper.selectsumcommentsmonth7(author_id);
        int m8=postMapper.selectsumcommentsmonth8(author_id);
        int m9=postMapper.selectsumcommentsmonth9(author_id);
        int m10=postMapper.selectsumcommentsmonth10(author_id);
        int m11=postMapper.selectsumcommentsmonth11(author_id);
        int m12=postMapper.selectsumcommentsmonth12(author_id);

        int n1=postMapper.selectsumcommentsnextmonth1(author_id);
        int n2=postMapper.selectsumcommentsnextmonth2(author_id);
        int n3=postMapper.selectsumcommentsnextmonth3(author_id);
        int n4=postMapper.selectsumcommentsnextmonth4(author_id);
        int n5=postMapper.selectsumcommentsnextmonth5(author_id);
        int n6=postMapper.selectsumcommentsnextmonth6(author_id);
        int n7=postMapper.selectsumcommentsnextmonth7(author_id);
        int n8=postMapper.selectsumcommentsnextmonth8(author_id);
        int n9=postMapper.selectsumcommentsnextmonth9(author_id);
        int n10=postMapper.selectsumcommentsnextmonth10(author_id);
        int n11=postMapper.selectsumcommentsnextmonth11(author_id);
        int n12=postMapper.selectsumcommentsnextmonth12(author_id);



        int[] arry=new int[]{x1,x2,x3,x4,x5,x6,x7,x8,x9,x10,x11,x12};
        int[] arryy= Arrays.copyOfRange(arry,startmonth-1,endmonth);
        int[] arrz=new int[]{z1,z2,z3,z4,z5,z6,z7,z8,z9,z10,z11,z12};
        int[] arrzz= Arrays.copyOfRange(arrz,startmonth-1,endmonth);
        int[] arrm=new int[]{m1+n1,m2+n2,m3+n3,m4+n4,m5+n5,m6+n6,m7+n7,m8+n8,m9+n9,m10+n10,m11+n11,m12+n12};
        int[] arrmm= Arrays.copyOfRange(arrm,startmonth-1,endmonth);

        int[] arrx=new int[endmonth-startmonth+1];
        for(int i=startmonth,y=0;i<=endmonth;i++,y++){
            arrx[y]=i;
            System.out.print("月"+arrx[y]+" ");
            System.out.print("发帖数"+arryy[y]+" ");
            System.out.print("提问数"+arrzz[y]+" ");
            System.out.print("评论数"+arrmm[y]+" ");
            System.out.println("");
        }
        Map<String, int[]> map1 = new HashMap<>();
                      map1.put("arrx",arrx);
                      map1.put("arry",arryy);
                      map1.put("arrz",arrzz);
                      map1.put("arrm",arrmm);
        System.out.println(map1);
        return new Result(Code.SUCCESS,"查找成功", map1);
    }


//修改
    @RequestMapping("/deletePostByID")
    public Result deletePostByID(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        postMapper.deletecontent(id);
        postMapper.deletePostByID(id);
        return new Result(Code.SUCCESS, "删除成功");
    }
}


