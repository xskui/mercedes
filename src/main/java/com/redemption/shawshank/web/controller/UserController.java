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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @RequiresPermissions("user:view")
    @RequestMapping(value = "/index")
    public String index(){
        return "manage/user/index";
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create")
    public String create(){
        return "manage/user/create";
    }

    @RequiresPermissions("user:add")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ResponBean addUser(User user){
        return userService.createUser(user);
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

    @RequiresPermissions("user:update")
    @RequestMapping(value = "/preupdate/{id}")
    public String update(@PathVariable("id") int id,ModelMap modelMap){
        User user = userService.findOne((long) id);
        modelMap.put("user",user);
        return "manage/user/update";
    }
    /**
     * 更新user
     * @param user
     * @return
     */
    @RequestMapping("/update/{id}")
    @RequiresPermissions("user:update")
    @ResponseBody
    public ResponBean update(@PathVariable("id") int id,User user){
        user.setId((long) id);
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
    @RequiresPermissions("user:view")
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> findAllUser(){
        UserExample example = new UserExample();
        List<User> list = userService.findAll(example);
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("rows",list);
        res.put("total",list.size());
        return res;
    }

}
