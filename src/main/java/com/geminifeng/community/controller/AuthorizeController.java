package com.geminifeng.community.controller;/**
 * description: AuthorizeController <br>
 * date: 2020/5/24 18:02 <br>
 * author: lenovo <br>
 * version: 1.0 <br>
 */

import com.geminifeng.community.dto.AccessTokenDTO;
import com.geminifeng.community.dto.GithubUser;
import com.geminifeng.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @ClassName AuthorizeController
 * @Author SunBaoFeng
 * @date 2020.05.24 18:02
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String redirect_uri;
    @Value("${github.redirect.uri}")
    private String client_secret;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, @RequestParam(name = "state") String state) {
        //封装请求参数对象
        AccessTokenDTO accessToken = new AccessTokenDTO();
        accessToken.setClient_id(client_id);
        accessToken.setClient_secret(redirect_uri);
        accessToken.setCode(code);
        accessToken.setRedirect_uri(client_secret);
        accessToken.setState(state);
        //获得GitHub的Token
        String back_token = githubProvider.getAccessToken(accessToken);
        //获得user信息
        GithubUser user = githubProvider.getUser(back_token);
        System.out.println("user\t" + user);
        return "index";
    }
}
