package com.sf.skytalk.controller;

import com.sf.skytalk.mapper.UserMapper;
import com.sf.skytalk.model.User2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request){
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
        return "index";
    }

}
