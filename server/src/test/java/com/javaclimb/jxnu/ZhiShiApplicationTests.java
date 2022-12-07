package com.javaclimb.jxnu;

import com.javaclimb.jxnu.controller.Code;
import com.javaclimb.jxnu.controller.Result;
import com.javaclimb.jxnu.controller.UserController;
import com.javaclimb.jxnu.dao.PostMapper;
import com.javaclimb.jxnu.dao.UserMapper;
import com.javaclimb.jxnu.domain.Contents;
import com.javaclimb.jxnu.domain.User;
import com.javaclimb.jxnu.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZhiShiApplicationTests {
    @Autowired
     UserMapper userMapper;
    @Test
    void contextLoads() {
        String password = "123";
        String passwordMd5 = MD5Utils.MD5Encode(password, "UTF-8");
        System.out.println(passwordMd5);
    }
    }


