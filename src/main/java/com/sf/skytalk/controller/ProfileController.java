package com.sf.skytalk.controller;

import com.sf.skytalk.dto.PaginationDTO;
import com.sf.skytalk.dto.QuestionDTO;
import com.sf.skytalk.model.User;
import com.sf.skytalk.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/profile/{action}")
    public String action(HttpServletRequest request,
                         @PathVariable("action") String action,
                         @RequestParam(value = "page",defaultValue = "1") int page,
                         @RequestParam(value = "size",defaultValue = "5") int size,
                         Model model){
        User user = (User) request.getSession().getAttribute("user");
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
        PaginationDTO<QuestionDTO> paginationDTO = questionService.list(user.getId(),page,size);
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }
}
