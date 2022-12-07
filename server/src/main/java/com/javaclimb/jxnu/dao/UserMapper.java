package com.javaclimb.jxnu.dao;

import com.javaclimb.jxnu.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserMapper {
    int verifyPassword(@Param("username") String username, @Param("password") String password);
    User findUserByName(String username);
    User findUserByNumber(String studentnumber);
    List<User> findAllUser();
    List<User> findUserBySchool(String professional);
    List<User> findUserByNameL(String username);
    List<User> findUserByNumberL(String studentnumber);
    List<User> select(String username,String professional,String studentnumber);
    List<User> select1(String username,String professional,String studentnumber);
    void addUser(String username,String password,String professional,String phoneNumber,String avatar,String studentnumber);
    void deleteUserByName(String username);


    void changeState(String username);
    void changeState2(String username);

    User login(String studentnumber,String password);


    void changeStatus(int id,String status);



    void deleteUserByID(int id);
    void deleteReportByID1(int id);
    void deleteReportByID2(int id);
    void deletecommentByID(int id);
    void deletecommentnextByID(int id);
    void deleteinterlocutionByID1(int id);
    void deleteinterlocutionByID2(int id);
    void deletepostByID(int id);
}
