package com.geminifeng.community.controller;/**
 * description: AuthorizeController <br>
 * date: 2020/5/24 18:02 <br>
 * author: lenovo <br>
 * version: 1.0 <br>
 */

import com.geminifeng.community.dto.AccessTokenDTO;
import com.geminifeng.community.dto.GithubUser;
import com.geminifeng.community.mapper.UserMapper;
import com.geminifeng.community.model.User;
import com.geminifeng.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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

    @Resource
    private UserMapper userMapper;

    @GetMapping("/getUser/{id}")
    @ResponseBody
    public User getUser(@PathVariable String id) {
        return userMapper.getUserById(id);
    }

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest req,
                           HttpServletResponse rsp) {
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
        GithubUser githubUser = githubProvider.getUser(back_token);
        System.out.println("user\t" + githubUser);
        if (null != githubUser) {
            //登录成功 写session 和 cookie
            req.getSession().setAttribute("user", githubUser);

            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            System.out.println("系统时间\t" + System.currentTimeMillis());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            System.out.println(user);
            userMapper.saveUser(user);
            //cookie
            rsp.addCookie(new Cookie("login_token", token));
            return "redirect:/";
        } else {
            //登录失败 重新登录
            return "redirect:/";
        }
    }
}
