package com.redemption.shawshank.web.service;

import com.redemption.shawshank.Constants;
import com.redemption.shawshank.dao.SysResourceMapper;
import com.redemption.shawshank.pojo.SysResource;
import com.redemption.shawshank.pojo.SysResourceExample;
import com.redemption.shawshank.utils.commonUtils.DateUtil;
import com.redemption.shawshank.utils.enums.ResourceType;
import com.redemption.shawshank.utils.enums.ServerCodeEnum;
import com.redemption.shawshank.web.base.ResponBean;
import com.redemption.shawshank.web.service.inter.ResourceService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author : xingshukui .
 * Date : 2017/7/5.
 * Desc :
 */
@Service
public class ResourceServiceImpl implements ResourceService{

    private static final Logger log = LoggerFactory.getLogger(ResourceServiceImpl.class);


    @Autowired(required = false)
    private SysResourceMapper sysResourceMapper;


    @Override
    public ResponBean create(SysResource sysResource) {
        if (sysResource == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        if (paramCheck(sysResource)){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_ELE_NULL);
        }

        log.info("资源新增接口，参数 ：" + Constants.gson.toJson(sysResource));
        sysResource.setAvailable((short)1);
        sysResource.setCreateTime(DateUtil.getCurrentDateTime());
        sysResource.setUpdateTime(DateUtil.getCurrentDateTime());

        try {
            int res = sysResourceMapper.insert(sysResource);
            if (res == 0){
                log.info("插入失败...");
                return ResponBean.ServerResponBean(ServerCodeEnum.DB_OPER_ERROR);
            }
            return ResponBean.successRespon();
        }catch (Exception e){
            e.printStackTrace();
            return ResponBean.errorResponBean();
        }
    }

    @Override
    public ResponBean delete(Long resourceId) {
        if (resourceId == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_ELE_NULL);
        }
        log.info("删除接口，参数："+resourceId);
        try {
            SysResource resource = new SysResource();
            resource.setId(resourceId);
            resource.setUpdateTime(DateUtil.getCurrentDateTime());
            resource.setAvailable((short)0);
            int res = sysResourceMapper.updateByPrimaryKeySelective(resource);
            if (res == 0){
                log.info("删除失败");
                return ResponBean.ServerResponBean(ServerCodeEnum.DB_OPER_ERROR);
            }
            return ResponBean.successRespon();
        }catch (Exception e){
            e.printStackTrace();
            return ResponBean.errorResponBean();
        }
    }

    @Override
    public ResponBean update(SysResource sysResource) {
        if (sysResource == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        if (paramCheck(sysResource)){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_ELE_NULL);
        }

        log.info("资源更新接口，参数：" + Constants.gson.toJson(sysResource));
        sysResource.setUpdateTime(DateUtil.getCurrentDateTime());

        try {
            int res = sysResourceMapper.updateByPrimaryKeySelective(sysResource);
            if (res == 0){
                log.info("更新失败");
                return ResponBean.ServerResponBean(ServerCodeEnum.DB_OPER_ERROR);
            }
            return ResponBean.successRespon();
        }catch (Exception e){
            e.printStackTrace();
            return ResponBean.errorResponBean();
        }
    }

    @Override
    public SysResource findone(Long resourceId) {
        return sysResourceMapper.selectByPrimaryKey(resourceId);
    }

    @Override
    public Set<String> findPermissions(Set<String> resourceId) {
        Set<String> set = new HashSet<String>();
        for (String id : resourceId){
            set.add(findone(Long.parseLong(id)).getPermission());
        }
        return set;
    }

    @Override
    public List<SysResource> findAll(SysResourceExample example) {
        return sysResourceMapper.selectByExample(example);
    }

    @Override
    public List<SysResource> findmenus(Set<String> permissions) {
        List<SysResource> allResource = findAll(new SysResourceExample());//没有条件
        List<SysResource> menus = new ArrayList<SysResource>();
        for(SysResource resource : allResource) {
            if(resource.isRootNode()) {
                continue;
            }
            if(StringUtils.isEmpty(resource.getType()) ||
                    !resource.getType().equals( ResourceType.MENU.getDesc())) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }


    private boolean hasPermission(Set<String> permissions, SysResource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

    private boolean paramCheck(SysResource resource){
        if (StringUtils.isEmpty(resource)){
            return false;
        }
        if (StringUtils.isEmpty(resource.getType())){
            return false;
        }
        if (StringUtils.isEmpty(resource.getPermission())){
            return false;
        }
        return true;
    }
}
