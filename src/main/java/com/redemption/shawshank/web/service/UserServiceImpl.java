package com.redemption.shawshank.web.service;

import com.redemption.shawshank.dao.UserMapper;
import com.redemption.shawshank.pojo.User;
import com.redemption.shawshank.pojo.UserExample;
import com.redemption.shawshank.utils.security.PasswordHelper;
import com.redemption.shawshank.web.service.inter.ResourceService;
import com.redemption.shawshank.web.service.inter.RoleService;
import com.redemption.shawshank.web.service.inter.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author : xingshukui .
 * Date : 2017/7/5.
 * Desc :
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;


    @Override
    public Integer createUser(User user) {
        //密码
        passwordHelper.encryptPassword(user);
        return userMapper.insert(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public void changePassword(Long userId, String newPassword) {

    }

    @Override
    public User findOne(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        UserExample example = new UserExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<User> list = userMapper.selectByExample(example);
        if ( !(list.size() > 0)){
            return null;
        }else {
            //fixme 这里默认name不会重复
            return list.get(0);
        }
    }

    @Override
    public Set<String> findRoles(String username) {
        Set<String> set = new HashSet<String>();
        User user = findByUsername(username);

        if (user != null && StringUtils.isNotEmpty(user.getRoleIds()) ){
            String[] rolesArr = user.getRoleIds().split(",");
            for (String str : rolesArr){
                set.add(roleService.findRoles(Long.parseLong(str)));
            }
        }
        return set;
    }

    @Override
    public Set<String> findPermissions(String username) {
        Set<String> set = new HashSet<String>();
        User user = findByUsername(username);

        if (user != null && StringUtils.isNotEmpty(user.getRoleIds())){

            String[] arr = user.getRoleIds().split(",");
            for (String str : arr){
                set.addAll(resourceService.findPermissions(roleService.findPermissions(Long.parseLong(str))));
            }
        }
        return set;
    }
}
