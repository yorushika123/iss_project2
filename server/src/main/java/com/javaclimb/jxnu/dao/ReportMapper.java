package com.javaclimb.jxnu.dao;

import com.javaclimb.jxnu.domain.Report;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {
    void addReport(Report report);
    List<Report> findAllReport();
    void updateReport(int id);
    List<Map<String,Object>> select(String progress, String report_name, String reported_name,String sort);

    void deleteComment(int comment_id);
    void deleteComment_next(int comment_id);


    void changestate(int id);
}
