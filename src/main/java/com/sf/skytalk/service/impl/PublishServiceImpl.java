package com.sf.skytalk.service.impl;

import com.sf.skytalk.mapper.QuestionMapper;
import com.sf.skytalk.model.Question;
import com.sf.skytalk.model.User2;
import com.sf.skytalk.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("publishService")
public class PublishServiceImpl implements PublishService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public boolean addQuestion(String title, String description, String tag, User2 user) {
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        return questionMapper.create(question) == 1 ? true : false;
    }
}
