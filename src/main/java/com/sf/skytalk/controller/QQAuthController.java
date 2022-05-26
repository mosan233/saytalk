package com.sf.skytalk.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.UUID;

public class QQAuthController {

    @Value("${qq.oauth.appId}")
    private String appId;

    @Value("${qq.oauth.appKey")
    private String appKey;

    @Value("${qq.oauth.redirectUrl}")
    private String redirectUrl;


    @GetMapping("/auth/qq")
    public String qqLogin(HttpServletRequest request){
        // 用于第三方应用防止CSRF攻击
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        request.getSession().setAttribute("state", uuid);

        // Step1：获取Authorization Code
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + appId +
                "&redirect_uri=" + URLEncoder.encode(redirectUrl) +
                "&state=" + uuid;

        return "redirect:"+redirectUrl;
    }

}
