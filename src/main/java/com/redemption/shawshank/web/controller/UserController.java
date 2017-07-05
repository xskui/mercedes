package com.redemption.shawshank.web.controller;

import com.redemption.shawshank.dao.UserMapper;
import com.redemption.shawshank.pojo.User;
import com.redemption.shawshank.pojo.UserExample;
import com.redemption.shawshank.web.service.inter.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Autowired
    private UserService userService;

    @RequestMapping("/1")
    public String u(){

        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(1L);

        logger.info("查询user信息开始.....");
        return userMapper.selectByExample(example).get(0).toString();
    }

    @RequiresPermissions("index")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(){

        User user = new User();
        short s = 0;
        user.setUsername("xingshukui");
        user.setPassword("123456");
        user.setLocked(s);
        user.setOrganizationId(1L);
        user.setRoleIds("1");

        int tmp = userService.createUser(user);

        if (tmp > 0){
            return "success";
        }

        return "fail";
    }

}
