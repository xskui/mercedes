package com.redemption.shawshank.web.controller;

import com.redemption.shawshank.pojo.Organization;
import com.redemption.shawshank.pojo.OrganizationExample;
import com.redemption.shawshank.utils.enums.ServerCodeEnum;
import com.redemption.shawshank.web.base.ResponBean;
import com.redemption.shawshank.web.service.inter.OrganizationService;
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
@RequestMapping("/organization")
public class OrganizationController {

    private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);


    @Autowired
    private OrganizationService organizationService;


    /**
     * 新增组织信息
     * @param organization
     * @return
     */
    @ResponseBody
    @RequiresPermissions("organization:add")
    @RequestMapping("/add")
    public ResponBean create(Organization organization){
        return organizationService.create(organization);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequiresPermissions("organization:update")
    @RequestMapping("/remove")
    public ResponBean remove(Long id){
        return organizationService.remove(id);
    }

    /**
     * 更新
     * @param organization
     * @return
     */
    @ResponseBody
    @RequiresPermissions("organization:update")
    @RequestMapping("/update")
    public ResponBean update(Organization organization){
        return organizationService.update(organization);
    }

    @ResponseBody
    @RequiresPermissions("organization:view")
    @RequestMapping("/query")
    public ResponBean query(Long id){

        if (id == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        Organization organization = organizationService.findOne(id);
        if (organization == null){
            log.info("id为：{}的组织数据记录为空",id.toString());
            return ResponBean.ServerResponBean(ServerCodeEnum.RESULT_NULL);
        }
        return ResponBean.successRespon(organization);
    }

    /**
     * 查询所有
     * @return
     */
    @ResponseBody
    @RequiresPermissions("organization:view")
    @RequestMapping("/list")
    public ResponBean findList(){
        OrganizationExample example = new OrganizationExample();
        List<Organization> list = organizationService.findAll(example);
        if (list.size() == 0){
            log.info("组织数据不存在");
            return ResponBean.ServerResponBean(ServerCodeEnum.RESULT_NULL);
        }
        return ResponBean.successRespon(list);
    }
}
