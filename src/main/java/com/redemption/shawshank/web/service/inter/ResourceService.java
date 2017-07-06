package com.redemption.shawshank.web.service.inter;

import com.redemption.shawshank.pojo.SysResource;

import java.util.Set;

/**
 * Author : xingshukui .
 * Date : 2017/7/5.
 * Desc :资源，也即是菜单/按钮项
 */
public interface ResourceService {

    /**
     * 新增
     * @param sysResource
     * @return
     */
    int create(SysResource sysResource);

    /**
     * 删除
     * @param resourceId
     * @return
     */
    int delete(Long resourceId);

    /**
     * 更新
     * @param sysResource
     * @return
     */
    int update(SysResource sysResource);

    /**
     * 查找某一个资源编号
     * @param resourceId
     * @return
     */
    SysResource findone(Long resourceId);


    /**
     * 查找资源编号
     * @param resourceId 资源id
     * @return
     */
    Set<String> findPermissions(Set<String> resourceId);
}