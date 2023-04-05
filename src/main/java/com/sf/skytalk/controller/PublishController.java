package com.sf.skytalk.controller;

import com.sf.skytalk.mapper.QuestionMapper;
import com.sf.skytalk.model.User;
import com.sf.skytalk.model.Question;
import com.sf.skytalk.service.QuestionService;
import com.sf.skytalk.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionMapper questionMapper;


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title
            , @RequestParam("description") String description
            , @RequestParam("tag") String tag
            , @RequestParam("id") Long id
            , HttpServletRequest request
            , Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("id",id);
        if(StringUtils.isEmpty(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(StringUtils.isEmpty(description)){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        if (StringUtils.isEmpty(tag)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/" ;
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Long id
            ,Model model){
        Question question = questionMapper.selectByPrimaryKey(id);
        model.addAttribute("id",id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        return "publish";
    }


}
