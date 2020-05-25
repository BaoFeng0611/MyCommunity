package com.geminifeng.community.controller;
/**
 * @Author: SunBaoFeng
 * @Description: PublishController
 * @Date: Created in 2020/5/25 23:13
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Description
 * @ClassName PublishController
 * @Author SunBaoFeng
 * @date 2020.05.25 23:13
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String commit(){

        return null;
    }
}
