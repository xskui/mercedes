package com.redemption.shawshank.web.service.inter;

import com.redemption.shawshank.pojo.User;
import com.redemption.shawshank.pojo.UserExample;
import com.redemption.shawshank.web.base.ResponBean;

import java.util.List;
import java.util.Set;

/**
 * Author : xingshukui .
 * Date : 2017/7/5.
 * Desc :
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     */
    ResponBean createUser(User user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    ResponBean updateUser(User user);


    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 根据用户id查找用户
     * @param userId
     * @return
     */
    User findOne(Long userId);

    /**
     * 获取所有的用户
     * @return
     */
    List<User> findAll(UserExample example);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

}
