package com.javaclimb.jxnu.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@Data
public class Comments {

    private int id;
    private int post_id;
    private String comments_content;
    private int user_id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date create_time;
     private   List<Comments_next> comments_next;
    private String username;
    private  String avatar;
    private boolean replyFlag=false;

}
