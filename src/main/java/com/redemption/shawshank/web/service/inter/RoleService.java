package com.redemption.shawshank.web.service.inter;

import com.redemption.shawshank.pojo.Role;
import com.redemption.shawshank.pojo.RoleExample;
import com.redemption.shawshank.web.base.ResponBean;

import java.util.List;
import java.util.Set;

/**
 * Author : xingshukui .
 * Date : 2017/7/5.
 * Desc :
 */
public interface RoleService {

    ResponBean createRole(Role role);

    ResponBean updateRole(Role role);

    ResponBean delRole(Long id);

    Role findOne(Long roleId);

    List<Role> findAll(RoleExample example);

    /**
     * 根据角色id得到角色标识符列表
     * @param roleId
     * @return
     */
    String findRoles(Long roleId);

    /**
     * 根据角色id得到权限字符串列表
     * @param roleId
     * @return
     */
    Set<String> findPermissions(Long roleId);
}
