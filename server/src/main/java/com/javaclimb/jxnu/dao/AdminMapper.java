package com.javaclimb.jxnu.dao;

import com.javaclimb.jxnu.domain.Admin;
import com.javaclimb.jxnu.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*管理员dao*/
@Mapper
public interface AdminMapper  {
    /*验证密码是否正确*/
    int verifyPassword(@Param("username") String username, @Param("password") String password);

    Admin findAll(@Param("username")String userName);
    void addAdmin(@Param("username")String userName,@Param("password")String password,@Param("phoneNumber")String phoneNumber, @Param("school_name")String school_name);
    List<Admin> findAllUser();
    void deleteUserByName(String username);
}
