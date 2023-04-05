package com.sf.skytalk.controller;

import com.sf.skytalk.dto.AccessTokenDTO;
import com.sf.skytalk.dto.GithubUserDTO;
import com.sf.skytalk.model.User;
import com.sf.skytalk.provider.GithubProvider;
import com.sf.skytalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.url}")
    private String redirectUrl;

    @Autowired
    private UserService userService;



    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * GitHub授权回调
     * @param code
     * @param state
     * @param response
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name="state")String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUrl);

        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessTokens = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDTO githubUserDTO = githubProvider.getUser(accessTokens);
        if(githubUserDTO != null){
            User user = new User();
            if(githubUserDTO.getId() != null){
                user.setAccountId(String.valueOf(githubUserDTO.getId()));
            }

            user.setName(githubUserDTO.getName());
            user.setAvatarUrl(githubUserDTO.getAvatarUrl());
            user.setToken(UUID.randomUUID().toString());
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",user.getToken()));
        }
        return "redirect:/";
    }

    /**
     * 注销
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/layout")
    public String layout(HttpServletRequest request
            ,HttpServletResponse response){
        //移除session中的user对象，以及从cookie中移除token
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @GetMapping("/toVirtualLogin")
    public String toVirtualLogin(HttpServletResponse response){
        response.addCookie(new Cookie("token", "0d385963-e74e-4436-b284-c2f6970e170e"));
        return "redirect:/";
    }
}
