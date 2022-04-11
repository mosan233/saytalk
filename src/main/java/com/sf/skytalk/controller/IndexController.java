package com.sf.skytalk.controller;

import com.sf.skytalk.dto.Pagination;
import com.sf.skytalk.dto.QuestionDTO;
import com.sf.skytalk.mapper.UserMapper;
import com.sf.skytalk.model.User2;
import com.sf.skytalk.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "size",defaultValue = "5") int size){
        System.out.println("index");
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("token".equals(cookie.getName())){
                    User2 user = userMapper.selectByToken(cookie.getValue());
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                }
            }
        }
        Pagination<QuestionDTO> data = questionService.list(page,size);
        model.addAttribute("pagination",data);
        return "index";
    }

}
