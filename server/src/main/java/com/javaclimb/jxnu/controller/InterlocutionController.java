package com.javaclimb.jxnu.controller;


import com.javaclimb.jxnu.dao.InterlocutionMapper;
import com.javaclimb.jxnu.domain.Interlocution;
import com.javaclimb.jxnu.domain.Post;
import com.javaclimb.jxnu.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/Interlocution")
@RestController
public class InterlocutionController {
    @Autowired
    InterlocutionMapper interlocutionMapper;

    @RequestMapping("/findAllInter")
    public Result findAllInter() {
        List<Interlocution> interlocutions = interlocutionMapper.findAllInter();
        System.out.println(interlocutions);
        return new Result(Code.SUCCESS,"查找成功",interlocutions);
    }

    @RequestMapping("/addInterlocution")
    public Result addInterlocution(@RequestBody Interlocution interlocution) {
        interlocution.setCreate_time(new Date());
        interlocutionMapper.addInterlocution(interlocution);
        return new Result(Code.SUCCESS, "发布成功");
    }

    @RequestMapping("/findInterlocution")
    public Result findInterlocution(HttpServletRequest request) {
        int experts_id = Integer.parseInt(request.getParameter("experts_id"));
        List<Map<String,Object>> interlocution = interlocutionMapper.findInterlocution(experts_id);
        if (!interlocution.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功",interlocution);
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping("/findInterlocution2")
    public Result findInterlocution2(HttpServletRequest request) {
        int creater_id = Integer.parseInt(request.getParameter("creater_id"));
        List<Map<String,Object>> interlocution = interlocutionMapper.findInterlocution2(creater_id);
        if (!interlocution.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功",interlocution);
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping("/findInterlocutionexperts")
    public Result findInterlocutionexperts(HttpServletRequest request) {
        int experts_id = Integer.parseInt(request.getParameter("experts_id"));
        List<Interlocution> interlocution = interlocutionMapper.findInterlocutionexperts(experts_id);
        if (!interlocution.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功",interlocution.get(0));
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping("/findInterlocutioncreater")
    public Result findInterlocutioncreater(HttpServletRequest request) {
        int creater_id = Integer.parseInt(request.getParameter("creater_id"));
        List<Interlocution> interlocution = interlocutionMapper.findInterlocutioncreater(creater_id);
        if (!interlocution.isEmpty()) {
            return new Result(Code.SUCCESS, "查找成功",interlocution.get(0));
        }
        return new Result(Code.FAIL, "查找失败");
    }

    @RequestMapping("/updateInterlocution")
    public Result updateInterlocution(@RequestBody Interlocution interlocution) {
        int id = interlocution.getId();
        String answer_content=interlocution.getAnswer_content();
        interlocution.setAnswer_time(new Date());
        System.out.println(interlocution.getAnswer_time());
        interlocutionMapper.updateInterlocution(id,answer_content,interlocution.getAnswer_time());
        return new Result(Code.SUCCESS, "发布成功");
    }

    @RequestMapping("/deleteByid")
    public Result deletenInterlocutionByID(HttpServletRequest request){
        int id= Integer.parseInt(request.getParameter("id"));
        interlocutionMapper.deleteByid(id);
        return  new Result(Code.SUCCESS,"删除成功");
    }

    @RequestMapping( "/findCreaterID")
    public Result findCreaterID(@RequestBody Interlocution interlocution){
        int create_id= interlocution.getCreater_id();
        User user=interlocutionMapper.findCreaterID(create_id);
        if(user ==null){
            return new Result(Code.FAIL,"失败");
        }
        return new Result(Code.SUCCESS,"成功",user);
    }

    @RequestMapping( "/findBYID")
    public Result findfindBYID(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        Interlocution interlocution= interlocutionMapper.findBYID(id);
        if(interlocution ==null){
            return new Result(Code.FAIL,"失败");
        }
        return new Result(Code.SUCCESS,"成功",interlocution);
    }

    @RequestMapping("/deleteInterlocution")
    public Result deleteInterlocution(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        interlocutionMapper.deleteInterlocution(id);
        return new Result(Code.SUCCESS,"删除成功");
    }


    @RequestMapping("/selectSumInterlocutionBYTime")
    public Result selectSumInterlocutionWeek(HttpServletRequest request) {
        int creater_id = Integer.parseInt(request.getParameter("creater_id"));
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        int x = interlocutionMapper.selectSumInterlocutionBYTime(creater_id,startTime,endTime);
        return new Result(Code.SUCCESS, "查找成功", x);
    }
}
