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
    PostMapper postMapper;
    @Test
    void contextLoads() {
        int startmonth=3;
        int endmonth=7;
        String s1="'2022-";
        String s2="-01'";
        String s3="-30'";
        String startmonth1=s1+startmonth+s2;
       String endmonth1=s1+startmonth+s3;
        System.out.println(startmonth1);
        System.out.println(endmonth1);
        int author_id=17;
        int[] x=new int[12];
        for(int i=startmonth;i<endmonth;i++)
        {
            x[i-1]=postMapper.selectsumpostmonth123(author_id,startmonth1,endmonth1);
            System.out.println(x[i-1]);
        }
    }
    }


