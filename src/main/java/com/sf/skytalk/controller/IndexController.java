package com.sf.skytalk.controller;


import com.sf.skytalk.dto.PaginationDTO;
import com.sf.skytalk.dto.QuestionDTO;
import com.sf.skytalk.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;



    @GetMapping("/")
    public String index(HttpServletRequest request,Model model,
                        @RequestParam(value = "page",defaultValue = "1") int page,
                        @RequestParam(value = "size",defaultValue = "5") int size){
        PaginationDTO<QuestionDTO> data = questionService.list(page,size);
        model.addAttribute("pagination",data);
        return "index";
    }

}
