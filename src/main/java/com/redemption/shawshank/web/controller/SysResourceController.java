package com.redemption.shawshank.web.controller;

import com.redemption.shawshank.pojo.SysResource;
import com.redemption.shawshank.pojo.SysResourceExample;
import com.redemption.shawshank.utils.enums.ServerCodeEnum;
import com.redemption.shawshank.web.base.ResponBean;
import com.redemption.shawshank.web.service.inter.ResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Author : xingshukui .
 * Date : 2017/7/8.
 * Desc :
 */
@Controller
@RequestMapping("/resource")
public class SysResourceController {

    private static final Logger log = LoggerFactory.getLogger(SysResourceController.class);


    @Autowired
    private ResourceService resourceService;

    /**
     * 资源新增
     * @param resource
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    @RequiresPermissions("resource:add")
    public ResponBean add(SysResource resource){
        return resourceService.create(resource);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    @RequiresPermissions("resource:update")
    public ResponBean remove(Long id){
        return resourceService.delete(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    @RequiresPermissions("resource:update")
    public ResponBean update(SysResource resource){
        return resourceService.update(resource);
    }

    @RequestMapping("/query")
    @ResponseBody
    @RequiresPermissions("resource:view")
    public ResponBean query(Long id){
        if (id == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        SysResource res = resourceService.findone(id);
        if (res == null){
            log.info("查询记录为空");
            return ResponBean.ServerResponBean(ServerCodeEnum.RESULT_NULL);
        }
        return ResponBean.successRespon(res);
    }

    @RequestMapping("/list")
    @ResponseBody
    @RequiresPermissions("resource:view")
    public ResponBean list(){
        SysResourceExample example = new SysResourceExample();
        List<SysResource> res = resourceService.findAll(example);
        if (res == null || res.size() == 0){
            log.info("查询记录为空");
            return ResponBean.ServerResponBean(ServerCodeEnum.RESULT_NULL);
        }
        return ResponBean.successRespon(res);
    }
}
