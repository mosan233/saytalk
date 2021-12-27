package com.sf.skytalk.controller;

import com.sf.skytalk.model.User2;
import com.sf.skytalk.service.PublishService;
import com.sf.skytalk.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private PublishService publishService;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
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

        User2 user = (User2) request.getSession().getAttribute("user");
        user = new User2();
        user.setId(1);
//        if(user == null){
//            model.addAttribute("error","用户未登录");
//            return "publish";
//        }
        if(publishService.addQuestion(title,description,tag,user)){
            return "redirect:/" ;
        }else {
            model.addAttribute("error","发布失败");
        }
        return "publish";
    }
}
