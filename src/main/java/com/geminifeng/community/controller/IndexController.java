package com.geminifeng.community.controller;/**
 * description: HelloController <br>
 * date: 2020/5/24 12:06 <br>
 * author: lenovo <br>
 * version: 1.0 <br>
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @ClassName HelloController
 * @Author SunBaoFeng
 * @date 2020.05.24 12:06
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
