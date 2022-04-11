package com.sf.skytalk.dto;

import com.sf.skytalk.model.Question;
import com.sf.skytalk.model.User2;
import lombok.Data;

@Data
public class QuestionDTO extends Question {
    private User2 user;
}
