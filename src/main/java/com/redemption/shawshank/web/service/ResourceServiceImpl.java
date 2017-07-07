package com.redemption.shawshank.web.service;

import com.redemption.shawshank.dao.SysResourceMapper;
import com.redemption.shawshank.pojo.SysResource;
import com.redemption.shawshank.pojo.SysResourceExample;
import com.redemption.shawshank.utils.enums.ResourceType;
import com.redemption.shawshank.web.service.inter.ResourceService;
import org.apache.shiro.authz.permission.WildcardPermission;
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

    @Autowired(required = false)
    private SysResourceMapper sysResourceMapper;


    @Override
    public int create(SysResource sysResource) {
        return sysResourceMapper.insert(sysResource);
    }

    @Override
    public int delete(Long resourceId) {
        return sysResourceMapper.deleteByPrimaryKey(resourceId);
    }

    @Override
    public int update(SysResource sysResource) {
        return sysResourceMapper.updateByPrimaryKey(sysResource);
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
}
