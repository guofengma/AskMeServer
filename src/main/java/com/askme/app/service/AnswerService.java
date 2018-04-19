package com.askme.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.askme.app.mapper.AnswerMapper;
import com.askme.app.model.Answer;

@Service
public class AnswerService {

	@Autowired
	AnswerMapper answerMapper;
	
	public List<Map<String,Object>> selectByQuestionId(Integer questionId){
		return answerMapper.selectByQuestionId(questionId);
	}
	
	public int insertSelective(Answer record){
		return answerMapper.insertSelective(record);
	}
	
}
