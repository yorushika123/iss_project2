package com.javaclimb.jxnu.domain;

import lombok.Data;
import org.joda.time.DateTime;

@Data
public class Comments_next {
    private int id;
    private int comments_id;
    private String contents;
    private DateTime create_time;
    private int user_id;
    private String username;
    private String avatar;
}
