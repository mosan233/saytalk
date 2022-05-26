package com.sf.skytalk.exception;

import com.sf.skytalk.model.Question;


public enum QuestionErrorCode implements CustomerErrorCode{

    QUESTION_NOT_FOUND("问题未找到");

    private String message;
    public String getMessage(){
        return message;
    }

    QuestionErrorCode(String message){
        this.message = message;
    }

}
