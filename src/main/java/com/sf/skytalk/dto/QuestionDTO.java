package com.sf.skytalk.dto;

import com.sf.skytalk.model.Question;
import com.sf.skytalk.model.User;
import lombok.Data;

@Data
public class QuestionDTO extends Question {
    private User user;
}
