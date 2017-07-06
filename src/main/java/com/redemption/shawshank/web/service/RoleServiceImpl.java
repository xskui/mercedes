package com.redemption.shawshank.web.service;

import com.redemption.shawshank.dao.RoleMapper;
import com.redemption.shawshank.pojo.Role;
import com.redemption.shawshank.web.service.inter.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Author : xingshukui .
 * Date : 2017/7/5.
 * Desc :
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Override
    public Integer createRole(Role role) {
        return roleMapper.insert(role);
    }

    @Override
    public Integer updateRole(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public Integer deleteRole(Long roleId) {
        return roleMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public Role findOne(Long roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public String findRoles(Long roleId) {
        Role role = findOne(roleId);
        return role.getRole();
    }

    @Override
    public Set<String> findPermissions(Long roleId) {

        Role role = findOne(roleId);
        List<String> list = new ArrayList<String>();

        if (StringUtils.isNotEmpty(role.getResourceIds())){
            list = Arrays.asList(role.getResourceIds().split(","));
        }
        return new HashSet<String>(list);
    }
}
