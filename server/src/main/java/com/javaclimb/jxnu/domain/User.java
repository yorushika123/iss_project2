package com.javaclimb.jxnu.domain;

import lombok.Data;
import org.joda.time.DateTime;
@Data
public class User {
    private  Integer id;/*主键*/

    private String username;/*账号*/

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
    private int  state;

}
