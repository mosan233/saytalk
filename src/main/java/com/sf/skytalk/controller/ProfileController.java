package com.sf.skytalk.controller;

import com.sf.skytalk.dto.Pagination;
import com.sf.skytalk.dto.QuestionDTO;
import com.sf.skytalk.mapper.UserMapper;
import com.sf.skytalk.model.User2;
import com.sf.skytalk.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;

    @RequestMapping("/profile/{action}")
    public String action(HttpServletRequest request,
                         @PathVariable("action") String action,
                         @RequestParam(value = "page",defaultValue = "1") int page,
                         @RequestParam(value = "size",defaultValue = "5") int size,
                         Model model){
        User2 user = (User2) request.getSession().getAttribute("user");
        if (user == null){
            return "redirect:/";
        }

        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        Pagination<QuestionDTO> pagination = questionService.list(user.getId(),page,size);
        model.addAttribute("pagination",pagination);
        return "profile";
    }
}
