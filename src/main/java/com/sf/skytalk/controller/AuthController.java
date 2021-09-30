package com.sf.skytalk.controller;

import com.sf.skytalk.dto.AccessTokenDTO;
import com.sf.skytalk.dto.GithubUser;
import com.sf.skytalk.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public void callback(@RequestParam(name = "code") String code,@RequestParam(name="state")String state){
        System.out.println(code+","+state);
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("2c40bab22405abf3edf0");
        accessTokenDTO.setClient_secret("32ff0e24e9dd70fa0285c3f77240f95add1d508f");
        accessTokenDTO.setRedirect_uri("http://localhost:8087/callback");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessTokens = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("token:"+accessTokens);
        GithubUser user = githubProvider.getUser(accessTokens);
        System.out.println(user.getName()+","+user.getBio());
    }
}
