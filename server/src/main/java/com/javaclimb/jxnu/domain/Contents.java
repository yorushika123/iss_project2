package com.javaclimb.jxnu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;

@Data
public class Contents {
    private  Integer id;/*主键*/

    private  String articleTitle;

    private  String articleCover;

    private  String articleContentHtml;

    private  String articleContentMd;

    private  String  articleAbstract;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date create_time;
}
