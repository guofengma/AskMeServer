package com.askme.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.askme.app.mapper.QuestionMapper;
import com.askme.app.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionMapper questionMapper;
	
	public int insertSelective(Question record){
		return questionMapper.insertSelective(record);
	}

	public Question selectByPrimaryKey(Integer id){
    	return questionMapper.selectByPrimaryKey(id);
    }

	public int updateByPrimaryKeySelective(Question record){
    	return questionMapper.updateByPrimaryKeySelective(record);
    }
	
	public List<Question> selectAll(){
		return questionMapper.selectAll();
	}
}
