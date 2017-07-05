package com.redemption.shawshank.web.service;

import com.redemption.shawshank.dao.SysResourceMapper;
import com.redemption.shawshank.pojo.SysResource;
import com.redemption.shawshank.web.service.inter.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Author : xingshukui .
 * Date : 2017/7/5.
 * Desc :
 */
public class ResourceServiceImpl implements ResourceService{

    @Autowired(required = false)
    private SysResourceMapper sysResourceMapper;


    @Override
    public int create(SysResource sysResource) {
        return 0;
    }

    @Override
    public int delete(Long resourceId) {
        return 0;
    }

    @Override
    public int update(SysResource sysResource) {
        return 0;
    }

    @Override
    public SysResource findone(Long resourceId) {
        return sysResourceMapper.selectByPrimaryKey(resourceId);
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceId) {
        Set<String> set = new HashSet<String>();

        for (long id : resourceId){
            set.add(findone(id).getPermission());
        }
        return set;
    }
}
