package com.sf.skytalk.exception;


public class CustomerException extends RuntimeException{
    private String message;

    public CustomerException(CustomerErrorCode errorCode){
        this.message = errorCode.getMessage();
    }

    public String getMessage(){
        return this.message;
    }
}
