package com.sf.skytalk.provider;

import com.alibaba.fastjson.JSON;
import com.sf.skytalk.dto.AccessTokenDTO;
import com.sf.skytalk.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class GithubProvider {

    public  String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String strResponse = response.body().string();
            String strAccessToken = strResponse.split("&")[0];
            return strAccessToken.split("=")[1];
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .addHeader("Authorization","Bearer "+accessToken)
                .build();
        try (Response response = client.newCall(request).execute()){
            if (response.code() == 200){
                String res = response.body().string();
                GithubUser githubUser = JSON.parseObject(res , GithubUser.class);
                return githubUser;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
