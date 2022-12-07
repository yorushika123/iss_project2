package com.javaclimb.jxnu.domain;

import lombok.Data;
import org.joda.time.DateTime;

import java.io.Serializable;
@Data
public class Admin implements Serializable {
     private  Integer id;/*主键*/

     private String name;/*账号*/

     private String password;/*密码*/


    private String phoneNumber;
    private String email;
    private String avatar;
    private DateTime create_time;
    private DateTime update_time;
    private String professional;
    private String studentnumber;
    private String truename;
    private String  status;
/*
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}
