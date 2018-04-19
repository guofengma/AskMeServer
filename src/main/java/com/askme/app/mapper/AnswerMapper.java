package com.askme.app.mapper;

import java.util.List;
import java.util.Map;

import com.askme.app.model.Answer;

public interface AnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
    
    List<Map<String,Object>> selectByQuestionId(Integer questionId);
}