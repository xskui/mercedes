package com.redemption.shawshank.web.service;

import com.redemption.shawshank.dao.UserMapper;
import com.redemption.shawshank.pojo.User;
import com.redemption.shawshank.pojo.UserExample;
import com.redemption.shawshank.utils.enums.ServerCodeEnum;
import com.redemption.shawshank.utils.security.PasswordHelper;
import com.redemption.shawshank.web.base.ResponBean;
import com.redemption.shawshank.web.service.inter.ResourceService;
import com.redemption.shawshank.web.service.inter.RoleService;
import com.redemption.shawshank.web.service.inter.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;


    @Override
    public ResponBean createUser(User user) {
        //密码
        if (user == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        if (StringUtils.isEmpty(user.getUsername())){
            return ResponBean.respon(ServerCodeEnum.PARM_ELE_NULL.code(),"用户名不能为空");
        }
        if (user.getOrganizationId() == null){
            return ResponBean.respon(ServerCodeEnum.PARM_ELE_NULL.code(),"用户所属组织不能为空");
        }
        user.setPassword("123456");//默认初始密码
        user.setMobile(user.getUsername());
        user.setLocked((short)0);
        passwordHelper.encryptPassword(user);

        try{
            int res = userMapper.insert(user);
            if (res <= 0){
                return ResponBean.ServerResponBean(ServerCodeEnum.DB_OPER_ERROR);
            }
            return ResponBean.successRespon();
        }catch (Exception e){
            e.printStackTrace();
            return ResponBean.errorResponBean();
        }
    }

    @Override
    public ResponBean updateUser(User user) {
        if (user == null || user.getId() == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        try{
            int res = userMapper.updateByPrimaryKeySelective(user);
            if (res <= 0){
                return ResponBean.ServerResponBean(ServerCodeEnum.DB_OPER_ERROR);
            }
            return ResponBean.successRespon();
        }catch (Exception e){
            e.printStackTrace();
            return ResponBean.errorResponBean();
        }
    }


    @Override
    public void changePassword(Long userId, String newPassword) {

    }

    @Override
    public User findOne(Long userId) {
        if (userId == null){
            return null;
        }
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> findAll(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public User findByUsername(String username) {

        if (StringUtils.isEmpty(username))return null;
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
