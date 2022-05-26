package com.sf.skytalk.service.impl;

import com.sf.skytalk.dto.Pagination;
import com.sf.skytalk.dto.QuestionDTO;
import com.sf.skytalk.exception.CustomerException;
import com.sf.skytalk.exception.QuestionErrorCode;
import com.sf.skytalk.mapper.QuestionMapper;
import com.sf.skytalk.mapper.UserMapper;
import com.sf.skytalk.model.Question;
import com.sf.skytalk.model.User2;
import com.sf.skytalk.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    @Override
    public Pagination<QuestionDTO> list(int page, int size) {
        Pagination pagination = new Pagination();
        int totalCount = questionMapper.count();
        pagination.setPagination(totalCount,page,size);

        List<Question> questionList = questionMapper.list((pagination.getPage()-1)*size,size);
        List<QuestionDTO> resultList = new ArrayList<>();
        for (Question question : questionList){
            User2 user = userMapper.selectById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            resultList.add(questionDTO);
        }
        pagination.setData(resultList);
        return pagination;
    }

    @Override
    public Pagination list(Integer userId, Integer page, Integer size) {
        Pagination pagination = new Pagination();
        int totalCount = questionMapper.countByUserId(userId);
        pagination.setPagination(totalCount,page,size);

        List<Question> questionList = questionMapper.listByUserId(userId,(pagination.getPage()-1)*size,size);
        List<QuestionDTO> resultList = new ArrayList<>();
        for (Question question : questionList){
            User2 user = userMapper.selectById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            resultList.add(questionDTO);
        }
        pagination.setData(resultList);
        return pagination;
    }

    @Override
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimary(id);
        if (question == null){
            throw new CustomerException(QuestionErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User2 user = userMapper.selectById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public boolean createOrUpdate(Question question) {
        if (question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            questionMapper.create(question);
        }else {
            question.setGmtModified(System.currentTimeMillis());
            int row = questionMapper.updateByPrimary(question);
            if (row != 1){
                throw new CustomerException(QuestionErrorCode.QUESTION_NOT_FOUND);
            }
        }
        return true;
    }
}
