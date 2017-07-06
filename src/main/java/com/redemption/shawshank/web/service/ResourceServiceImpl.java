package com.redemption.shawshank.web.service;

import com.redemption.shawshank.dao.SysResourceMapper;
import com.redemption.shawshank.pojo.SysResource;
import com.redemption.shawshank.pojo.SysResourceExample;
import com.redemption.shawshank.web.service.inter.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
