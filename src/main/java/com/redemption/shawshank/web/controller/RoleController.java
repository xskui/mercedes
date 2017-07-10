package com.redemption.shawshank.web.controller;

import com.google.gson.Gson;
import com.redemption.shawshank.Constants;
import com.redemption.shawshank.pojo.Role;
import com.redemption.shawshank.pojo.RoleExample;
import com.redemption.shawshank.utils.enums.ServerCodeEnum;
import com.redemption.shawshank.web.base.BaseController;
import com.redemption.shawshank.web.base.ResponBean;
import com.redemption.shawshank.web.service.inter.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author : xingshukui .
 * Date : 2017/7/7.
 * Desc :
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private RoleService roleService;

    /**
     * 首页
     * @return
     */
    @RequiresPermissions("role:view")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "manage/role/index";
    }
    /**
     * 新增角色
     * @param role
     * @return
     */
    @RequestMapping("/add")
    @RequiresPermissions("role:add")
    @ResponseBody
    public ResponBean create(@RequestBody Role role){
        return roleService.createRole(role);
    }

    /**
     *
     * @param role
     * @return
     */
    @RequestMapping("/update")
    @RequiresPermissions("role:update")
    @ResponseBody
    public ResponBean update(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    /**
     * 根据id删除对应role，修改avaliable=1
     * @param id
     * @return
     */
    @RequiresPermissions("role:update")
    @ResponseBody
    @RequestMapping("/del")
    public ResponBean delRole(Long id){
        return roleService.delRole(id);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequiresPermissions("role:view")
    @RequestMapping("/query")
    @ResponseBody
    public ResponBean query(Long id){
        Role role = roleService.findOne(id);
        if (role == null)return ResponBean.ServerResponBean(ServerCodeEnum.RESULT_NULL);
        return ResponBean.successRespon(role);
    }

    /**
     * 查询全部--后期需做分页
     * @return
     */
    @RequiresPermissions("role:view")
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> queryAll(){
        RoleExample example = new RoleExample();
        List<Role> list = roleService.findAll(example);
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("rows",list);
        res.put("total",list.size());
        return res;

    }
}
