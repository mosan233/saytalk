package com.sf.skytalk.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler
    ModelAndView handle(HttpServletRequest request,Throwable throwable){
//        HttpStatus status = getStatus(request);
        ModelAndView view = new ModelAndView();
        if (throwable instanceof RuntimeException){
            view.addObject("message",throwable.getMessage());
        }else {
            view.addObject("message","服务器冒烟了，请稍后重试");
        }
        //出现异常返回的页面
        view.setViewName("error");
        return view;
    }

    private HttpStatus getStatus(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
