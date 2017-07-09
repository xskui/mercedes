package com.redemption.shawshank.web.service;

import com.redemption.shawshank.dao.RoleMapper;
import com.redemption.shawshank.pojo.Role;
import com.redemption.shawshank.pojo.RoleExample;
import com.redemption.shawshank.utils.commonUtils.DateUtil;
import com.redemption.shawshank.utils.enums.ServerCodeEnum;
import com.redemption.shawshank.web.base.ResponBean;
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
@Service()
public class RoleServiceImpl implements RoleService {


    @Autowired(required = false)
    private RoleMapper roleMapper;

    @Override
    public ResponBean createRole(Role role) {
        if (role == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }

        if (StringUtils.isEmpty(role.getRole()) || StringUtils.isEmpty(role.getDescription())){
            return ResponBean.respon(ServerCodeEnum.PARM_ELE_NULL.code(),"参数角色名称或者角色描述不能为空");
        }

        role.setAvailable((short)1);//默认1有效
        role.setCreateTime(DateUtil.getCurrentDateTime());
        role.setUpdateTime(DateUtil.getCurrentDateTime());

        try{
            int res = roleMapper.insert(role);
            if (res <= 0){
                return ResponBean.ServerResponBean(ServerCodeEnum.DB_OPER_ERROR);
            }
            return ResponBean.successRespon();
        }catch (Exception e){
            e.printStackTrace();
            return ResponBean.ServerResponBean(ServerCodeEnum.ERROR);
        }
    }

    @Override
    public ResponBean updateRole(Role role) {

        if (role == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        if (StringUtils.isEmpty(role.getRole()) || StringUtils.isEmpty(role.getDescription())){
            return ResponBean.respon(ServerCodeEnum.PARM_ELE_NULL.code(),"参数角色名称或者角色描述不能为空");
        }

        try {
            int res = roleMapper.updateByPrimaryKeySelective(role);
            if (res <= 0){
                return ResponBean.ServerResponBean(ServerCodeEnum.DB_OPER_ERROR);
            }
            return ResponBean.successRespon();
        }catch (Exception e){
            e.printStackTrace();
            return ResponBean.ServerResponBean(ServerCodeEnum.ERROR);
        }
    }

    @Override
    public ResponBean delRole(Long id) {
        if (id == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        try {
            Role r = new Role();
            r.setId(id);
            r.setAvailable((short)1);
            int res = roleMapper.updateByPrimaryKeySelective(r);
            if (res == 0){
                return ResponBean.ServerResponBean(ServerCodeEnum.DB_OPER_ERROR);
            }
            return ResponBean.successRespon();
        }catch (Exception e){
            e.printStackTrace();
            return ResponBean.errorResponBean();
        }
    }

    @Override
    public Role findOne(Long roleId) {
        if (roleId == null)return null;
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public List<Role> findAll(RoleExample example) {
        if (example == null)return null;
        return roleMapper.selectByExample(example);
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
