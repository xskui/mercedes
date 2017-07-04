package com.redemption.shawshank.web.controller;

import com.redemption.shawshank.dao.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : xingshukui .
 * Date : 2017/7/4.
 * Desc :
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired(required = false)
    private UserMapper userMapper;

    @RequestMapping("/1")
    public String u(){

        logger.info("查询user信息开始.....");
        return userMapper.selectByPrimaryKey(1).toString();
    }

}