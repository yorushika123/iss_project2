package com.javaclimb.jxnu.dao;

import com.javaclimb.jxnu.domain.Interlocution;
import com.javaclimb.jxnu.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface InterlocutionMapper {
    List<Interlocution> findAllInter();
    void addInterlocution(Interlocution interlocution);
    List<Map<String,Object>> findInterlocution(int experts_id);
    List<Map<String,Object>> findInterlocution2(int creater_id);
    List<Interlocution> findInterlocutionexperts(int experts_id);
    List<Interlocution> findInterlocutioncreater(int creater_id);
    void updateInterlocution(int id, String answer_content, Date answer_time);
    void deleteByid(int id);
    User findCreaterID(int creater_id);
    Interlocution findBYID(int id);
    void deleteInterlocution(int id);
    int selectSumInterlocutionBYTime(int creater_id,String startTime,String endTime);
}
