package com.sf.skytalk.controller;

import com.sf.skytalk.dto.AccessTokenDTO;
import com.sf.skytalk.dto.GithubUser;
import com.sf.skytalk.mapper.UserMapper;
import com.sf.skytalk.model.User2;
import com.sf.skytalk.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name="state")String state,
                           HttpServletResponse response){
        System.out.println(code+","+state);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("2c40bab22405abf3edf0");
        accessTokenDTO.setClient_secret("90ae26e701c92baf6692d85c19ddf2ee106fbd02");
        accessTokenDTO.setRedirect_uri("http://localhost:8087/callback");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessTokens = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("token:"+accessTokens);
        GithubUser githubUser = githubProvider.getUser(accessTokens);
        if(githubUser != null){
            User2 user = new User2();
            if(githubUser.getId() != null){
                user.setAccountId(String.valueOf(githubUser.getId()));
            }
            user.setGmtCreate(System.currentTimeMillis());
            user.setName(githubUser.getName());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setToken(UUID.randomUUID().toString());
            userMapper.insertUser(user);
            response.addCookie(new Cookie("token",user.getToken()));
        }
        return "redirect:/";
    }
}
