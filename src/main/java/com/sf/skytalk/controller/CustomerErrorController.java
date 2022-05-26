package com.sf.skytalk.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class CustomerErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return null;
    }


    @RequestMapping(
            produces = {"text/html"}
    )
    public ModelAndView errorHtml(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        HttpStatus status = getStatus(request);
        if (status.is4xxClientError()){
            view.addObject("message","你的访问姿势不对，请换个姿势再来");
        }else if (status.is5xxServerError()){
            view.addObject("message","服务器冒烟了，请稍后重试");
        }
        view.setViewName("error");
        return view;
    }

    protected HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }

}

