package com.redemption.shawshank.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author : xingshukui
 * date : 2017/7/6
 * desc :
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }


}
