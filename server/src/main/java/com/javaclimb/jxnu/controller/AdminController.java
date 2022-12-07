package com.javaclimb.jxnu.controller;


import com.alibaba.fastjson.JSONObject;
import com.javaclimb.jxnu.dao.AdminMapper;
//import com.javaclimb.jxnu.service.AdminService;
import com.javaclimb.jxnu.domain.Admin;
import com.javaclimb.jxnu.domain.Post;
import com.javaclimb.jxnu.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class AdminController {
//
//    @Autowired
//    private AdminService adminservice;

    @Autowired
    private AdminMapper adminMapper;

//判断是否登录成功

    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
    public Object loginStatus(HttpServletRequest request, HttpSession session) {
        JSONObject jsonobject = new JSONObject();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean flag = adminMapper.verifyPassword(name, password) > 0;
        if (flag) {
            jsonobject.put(Consts.CODE, 1);
            jsonobject.put(Consts.MSG, "登录成功");
            session.setAttribute(Consts.NAME, name);
            return jsonobject;
        }
        jsonobject.put(Consts.CODE, 0);
        jsonobject.put(Consts.MSG, "用户名或密码错误");
        return jsonobject;
    }

}
