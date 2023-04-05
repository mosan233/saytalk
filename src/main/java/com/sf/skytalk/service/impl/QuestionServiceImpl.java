package com.sf.skytalk.service.impl;

import com.sf.skytalk.dto.PaginationDTO;
import com.sf.skytalk.dto.QuestionDTO;
import com.sf.skytalk.exception.CustomerException;
import com.sf.skytalk.exception.QuestionErrorCode;
import com.sf.skytalk.mapper.QuestionMapper;
import com.sf.skytalk.mapper.UserMapper;
import com.sf.skytalk.model.QuestionExample;
import com.sf.skytalk.model.User;
import com.sf.skytalk.model.Question;
import com.sf.skytalk.service.QuestionService;
import org.apache.ibatis.session.RowBounds;
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
    public PaginationDTO<QuestionDTO> list(int page, int size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample example = new QuestionExample();
        int totalCount = questionMapper.countByExample(example);
        paginationDTO.setPagination(totalCount,page,size);

        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds((paginationDTO.getPage()-1)*size, size));
        List<QuestionDTO> resultList = new ArrayList<>();
        for (Question question : questionList){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            resultList.add(questionDTO);
        }
        paginationDTO.setData(resultList);
        return paginationDTO;
    }

    @Override
    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        int totalCount = questionMapper.countByExample(example);
        paginationDTO.setPagination(totalCount,page,size);

        List<Question> questionList = questionMapper.selectByExampleWithBLOBsWithRowbounds(example, new RowBounds((paginationDTO.getPage()-1)*size, size));
        List<QuestionDTO> resultList = new ArrayList<>();
        for (Question question : questionList){
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            resultList.add(questionDTO);
        }
        paginationDTO.setData(resultList);
        return paginationDTO;
    }

    @Override
    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomerException(QuestionErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public boolean createOrUpdate(Question question) {
        if (question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            questionMapper.insertSelective(question);
        }else {
            question.setGmtModified(System.currentTimeMillis());
            int row = questionMapper.updateByPrimaryKeySelective(question);
            if (row != 1){
                throw new CustomerException(QuestionErrorCode.QUESTION_NOT_FOUND);
            }
        }
        return true;
    }
}
