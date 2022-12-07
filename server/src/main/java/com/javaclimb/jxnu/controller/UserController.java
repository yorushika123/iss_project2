package com.javaclimb.jxnu.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.jxnu.dao.UserMapper;
import com.javaclimb.jxnu.domain.User;
import com.javaclimb.jxnu.utils.Consts;
import com.javaclimb.jxnu.utils.MD5Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RestController
@RequestMapping("/User")
public class UserController {
//
//    @Autowired
//    private AdminService adminservice;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping( "/registerStatus")
    public Result registerStatus(@NotNull HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String professional = request.getParameter("professional");
        String studentnumber = request.getParameter("studentnumber");
        String avatar=request.getParameter("img1");
        User user = userMapper.findUserByName(username);
        if (user == null) {
            String passwordMd5 = MD5Utils.MD5Encode(password, "UTF-8");
            userMapper.addUser(username, passwordMd5,professional,phoneNumber,avatar,studentnumber);
            return new Result(Code.SUCCESS, "注册成功");
        }
        return new Result(Code.FAIL, "注册失败");
    }

    @RequestMapping("/findAllUser")
    public Result findAllUser() {
        List<User> user = userMapper.findAllUser();
        return new Result(Code.SUCCESS, "查找成功", user);
    }

    @RequestMapping("/deleteUserByName")
    public Result deleteUserByName(HttpServletRequest request) {
        String username = request.getParameter("username");
        userMapper.deleteUserByName(username);
        return new Result(Code.SUCCESS, "删除成功");
    }

    @RequestMapping("/findUserByName")
    public Result findUserByName(HttpServletRequest request) {
        String username = request.getParameter("username");
        User user = userMapper.findUserByName(username);
        if (user != null) {
            return new Result(Code.SUCCESS, "查找成功", user);
        }
            return new Result(Code.FAIL, "没有信息");
    }
    @RequestMapping("/findUserByName2")
    public Result findUserByName2(@RequestBody User user) {
        String username = user.getUsername();
        User user2 = userMapper.findUserByName(username);
        if (user2 != null) {
            return new Result(Code.SUCCESS, "查找成功", user2);
        }
        return new Result(Code.FAIL, "没有信息");
    }
    @RequestMapping("/findUserByNameL")
    public Result findUserByNameL(HttpServletRequest request) {
        String username = request.getParameter("username");
        List<User> user= userMapper.findUserByNameL(username);
        if (!isEmpty(user)) {
            return new Result(Code.SUCCESS, "查找成功", user);
        }
        return new Result(Code.FAIL, "没有信息");
    }

    @RequestMapping("/findUserByNumber")
    public Result findUserByNumber(HttpServletRequest request) {
        String studentnumber = request.getParameter("studentnumber");
        User user = userMapper.findUserByNumber(studentnumber);
        if (user != null) {
            return new Result(Code.SUCCESS, "查找成功", user);
        }
        return new Result(Code.FAIL, "没有信息");
    }

    @RequestMapping("/findUserByNumberL")
    public Result findUserByNumberL(HttpServletRequest request) {
        String studentnumber = request.getParameter("studentnumber");
        List<User> user = userMapper.findUserByNumberL(studentnumber);
        if (!isEmpty(user)) {
            return new Result(Code.SUCCESS, "查找成功", user);
        }
        return new Result(Code.FAIL, "没有信息");
    }


    @RequestMapping("/findUserBySchool")
    public Result findUserBySchool(HttpServletRequest request) {
        String professional = request.getParameter("professional");
        List<User> user = userMapper.findUserBySchool(professional);
        if (!isEmpty(user)) {
            return new Result(Code.SUCCESS, "查找成功", user);
        }
        return new Result(Code.FAIL, "没有信息");
    }

    @RequestMapping("/select")
    public Result select(HttpServletRequest request){
        String professional = request.getParameter("professional");
        String studentnumber = request.getParameter("studentnumber");
        String username = request.getParameter("username");
        List<User> user=userMapper.select(username,professional,studentnumber);
            if(professional==null||professional==""){
                if(userMapper.findUserByNumber(studentnumber).equals( userMapper.findUserByName(username))){
                    return new Result(Code.SUCCESS, "查找成功", userMapper.findUserByNumberL(studentnumber) );
                }
                else
                    return new Result(Code.FAIL, "没有信息");

            }
            if(username==null||username==""){
                if( userMapper.findUserBySchool(professional).contains(userMapper.findUserByNumber(studentnumber))){
                    return new Result(Code.SUCCESS, "查找成功", userMapper.findUserByNumberL(studentnumber) );
                }
                else
                    return new Result(Code.FAIL, "没有信息");
            }
            if(studentnumber==null||studentnumber==""){
                if( userMapper.findUserBySchool(professional).contains(userMapper.findUserByName(username))){
                    return new Result(Code.SUCCESS, "查找成功", userMapper.findUserByNameL(username));
                }
                else
                    return new Result(Code.FAIL, "没有信息");
            }
            if(!isEmpty(user)){
                return new Result(Code.SUCCESS, "查找成功", user);
            }
            else
                return new Result(Code.FAIL, "没有信息");
    }
    @RequestMapping("/select1")
    public Result select1(HttpServletRequest request){
        String professional = request.getParameter("professional");
        String studentnumber = request.getParameter("studentnumber");
        String username = request.getParameter("username");

        professional ="%"+professional+"%";
        studentnumber ="%"+studentnumber+"%";
        username ="%"+username+"%";
//        System.out.println(professional);
//        System.out.println(studentnumber);
//        System.out.println(username);
        List<User> user=userMapper.select1(username,professional,studentnumber);
        System.out.println(user);
        if(!isEmpty(user)){
            return new Result(Code.SUCCESS, "查找成功", user);
        }else
            return new Result(Code.FAIL, "没有信息");
    }

    @RequestMapping("/changestate")
    public Result changestate(HttpServletRequest request) {
              String username=request.getParameter("username");
              userMapper.changeState(username);
              return new Result(Code.SUCCESS, "禁言成功");
    }

    @RequestMapping("/changestate2")
    public Result changestate2(HttpServletRequest request) {
        String username=request.getParameter("username");
        userMapper.changeState2(username);
        return new Result(Code.SUCCESS, "取消禁言成功");
    }


    @RequestMapping("/login")
    public Result Login(@RequestBody User users) {
   /*     String password=request.getParameter("password");
        String studentnumber =request.getParameter("studentnumber");*/
        String password=users.getPassword();
        String passwordMd5 = MD5Utils.MD5Encode(password, "UTF-8");
        User user =userMapper.login(users.getStudentnumber(),passwordMd5);

        return new Result(Code.SUCCESS, "查找成功", user);
    }


    @RequestMapping("/changestatus1")
    public Result changestatus1(HttpServletRequest request) {
        int id=Integer.parseInt(request.getParameter("id"));
        String status=request.getParameter("status");
        userMapper.changeStatus(id,status);
        return new Result(Code.SUCCESS, "设置成功");
    }





    @RequestMapping("/deleteUserByID")
    public Result deleteUserByID(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        userMapper.deletecommentnextByID(id);
        userMapper.deletecommentByID(id);
        userMapper.deleteinterlocutionByID1(id);
        userMapper.deleteinterlocutionByID2(id);
        userMapper.deleteReportByID1(id);
        userMapper.deleteReportByID2(id);
        userMapper.deletepostByID(id);
        userMapper.deleteUserByID(id);
        return new Result(Code.SUCCESS, "删除成功");
    }
}
