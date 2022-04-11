package com.sf.skytalk.service;

import com.sf.skytalk.dto.Pagination;
import com.sf.skytalk.dto.QuestionDTO;
import com.sf.skytalk.model.User2;

public interface QuestionService {
    boolean addQuestion(String title, String description, String tag, User2 user);

    Pagination<QuestionDTO> list(int page, int size);

    Pagination list(Integer id, Integer page, Integer size);
}
