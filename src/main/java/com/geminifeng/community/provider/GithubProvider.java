package com.geminifeng.community.provider;/**
 * description: GithubProvider <br>
 * date: 2020/5/24 18:06 <br>
 * author: lenovo <br>
 * version: 1.0 <br>
 */

import com.alibaba.fastjson.JSON;
import com.geminifeng.community.dto.AccessTokenDTO;
import com.geminifeng.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description
 * @ClassName GithubProvider
 * @Author SunBaoFeng
 * @date 2020.05.24 18:06
 */
@Component
public class GithubProvider {
    /**
     * OKHTTP   POST请求
     *
     * @param accessTokenDTO
     * @return
     */
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON.toJSONString(accessTokenDTO), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String backString = response.body().string();
            String accessToken = backString.split("&")[0].split("=")[1];
            System.out.println("accessToken\t" + accessToken);
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * OKHTTP   GET请求
     *
     * @param accessToken
     * @return
     */
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String backString = response.body().string();
            GithubUser githubUser = JSON.parseObject(backString, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
