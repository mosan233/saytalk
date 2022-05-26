package com.sf.skytalk.service;

import com.sf.skytalk.dto.Pagination;
import com.sf.skytalk.dto.QuestionDTO;
import com.sf.skytalk.model.Question;

public interface QuestionService {

    Pagination<QuestionDTO> list(int page, int size);

    Pagination list(Integer id, Integer page, Integer size);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    QuestionDTO getById(Integer id);

    /**
     *创建或更新问题
     * @param question
     * @return
     */
    boolean createOrUpdate(Question question);
}
