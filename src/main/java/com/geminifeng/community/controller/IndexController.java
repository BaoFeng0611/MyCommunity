package com.geminifeng.community.controller;/**
 * description: HelloController <br>
 * date: 2020/5/24 12:06 <br>
 * author: lenovo <br>
 * version: 1.0 <br>
 */

import com.geminifeng.community.mapper.UserMapper;
import com.geminifeng.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @ClassName IndexController
 * @Author SunBaoFeng
 * @date 2020.05.24 12:06
 */
@Controller
public class IndexController {

    @Resource
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("")) {
                String token = cookie.getValue();
                User user = userMapper.findByToken(token);
                if (null != user) {
                    req.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        return "index";
    }
}
