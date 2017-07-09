package com.redemption.shawshank.web.controller;

import com.redemption.shawshank.dao.UserMapper;
import com.redemption.shawshank.pojo.User;
import com.redemption.shawshank.pojo.UserExample;
import com.redemption.shawshank.utils.enums.ServerCodeEnum;
import com.redemption.shawshank.web.base.BaseController;
import com.redemption.shawshank.web.base.ResponBean;
import com.redemption.shawshank.web.service.inter.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author : xingshukui .
 * Date : 2017/7/4.
 * Desc :
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

/*    @RequestMapping("/1")
    @RequiresPermissions("user2:view")
    @ResponseBody
    public String u(){

        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(1L);

        logger.info("查询user信息开始.....");
        return userMapper.selectByExample(example).get(0).toString();
    }*/

    @RequiresPermissions("user:add")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addUser(@RequestBody User user){

        //fixme 这里需要规定新增之后的跳转页面
        userService.createUser(user);
        return null;
    }

    /**
     * 根据主键id，删除user，修改状态
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    @RequiresPermissions("user:update")
    @ResponseBody
    public ResponBean delUser(Long id){
        User u = new User();
        u.setId(id);
        u.setLocked((short)1);
        return userService.updateUser(u);
    }

    /**
     * 更新user
     * @param user
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("user:update")
    @ResponseBody
    public ResponBean update(User user){
        return userService.updateUser(user);
    }

    /**
     * 根据id单查user
     * @param id
     * @return
     */
    @RequestMapping("/query")
    @RequiresPermissions("user:view")
    @ResponseBody
    public ResponBean findUser(Long id){
        User u = userService.findOne(id);
        if (u == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.RESULT_NULL);
        }
        return ResponBean.successRespon(u);
    }

    /**
     * 查找所有的user
     * @return
     */
    @RequiresPermissions("usere:view")
    @RequestMapping("/queryAll")
    @ResponseBody
    public ResponBean findAllUser(){
        UserExample example = new UserExample();
        List<User> list = userService.findAll(example);
        if (list == null || list.size() == 0){
            return ResponBean.ServerResponBean(ServerCodeEnum.RESULT_NULL);
        }
        return ResponBean.successRespon(list);
    }

}
