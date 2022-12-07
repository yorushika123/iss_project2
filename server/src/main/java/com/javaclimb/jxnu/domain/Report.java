package com.javaclimb.jxnu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class Report {
   private int id;
   private String report_name;
   private String reported_name;
   private String report_reason;
   private String reported_content;
   private int report_id;
   private int reported_id;
   private int progress;
  private int comments_id;
   private String sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date report_time;
}
