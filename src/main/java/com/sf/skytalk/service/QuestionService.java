package com.sf.skytalk.service;

import com.sf.skytalk.dto.PaginationDTO;
import com.sf.skytalk.dto.QuestionDTO;
import com.sf.skytalk.model.Question;

public interface QuestionService {

    PaginationDTO<QuestionDTO> list(int page, int size);

    PaginationDTO list(Long userId, Integer page, Integer size);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    QuestionDTO getById(Long id);

    /**
     *创建或更新问题
     * @param question
     * @return
     */
    boolean createOrUpdate(Question question);
}
