package com.javaclimb.jxnu.controller;

import com.javaclimb.jxnu.dao.ReportMapper;
import com.javaclimb.jxnu.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Report")
public class ReportController {

    @Autowired
    ReportMapper reportMapper;


    @RequestMapping("/addReport")
    public Result addReport(@RequestBody Report report){
        reportMapper.addReport(report);
        return new Result(Code.SUCCESS, "举报成功");
    }

    @RequestMapping("/findAllReport")
    public Result findAllReport(){
        List<Report> report=reportMapper.findAllReport();
        if(!report.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功", report);
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping("/updateReport")
    public Result updateReport(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        reportMapper.updateReport(id);
            return new Result(Code.SUCCESS, "处理成功");

    }

    @RequestMapping("/select")
    public Result select(HttpServletRequest request) {
        String progress =request.getParameter("progress");
        String report_name = request.getParameter("report_name");
        String reported_name = request.getParameter("reported_name");
        String sort = request.getParameter("sort");
        List<Map<String,Object>> report = reportMapper.select(progress,report_name,reported_name,sort);
        if (!report.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功", report);
        }
        return new Result(Code.FAIL,"查找失败");
    }








    @RequestMapping("/deleteComment")
    public Result deleteComment(HttpServletRequest request){
        int comment_id=Integer.parseInt(request.getParameter("comment_id"));
        reportMapper.deleteComment_next(comment_id);
        reportMapper.deleteComment(comment_id);
        return new Result(Code.SUCCESS, "删除成功");

    }

    @RequestMapping("/changestate")
    public Result changestate(HttpServletRequest request) {
       int id=Integer.parseInt(request.getParameter("id"));
        reportMapper.changestate(id);
        return new Result(Code.SUCCESS, "禁言成功");
    }
}
