package com.redemption.shawshank.web.service.inter;

import com.redemption.shawshank.pojo.Organization;
import com.redemption.shawshank.pojo.OrganizationExample;
import com.redemption.shawshank.web.base.ResponBean;

import java.util.List;

/**
 * Author : xingshukui .
 * Date : 2017/7/7.
 * Desc : 组织信息server
 */
public interface OrganizationService {

    /**
     * 新增组织
     * @param organization
     * @return
     */
    ResponBean create(Organization organization);

    /**
     * 根据organizationID来更新组织信息
     * @param organization
     * @return
     */
    ResponBean update(Organization organization);

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    ResponBean remove(Long id);

    /**
     * 根据id查询单条记录
     * @param id
     * @return
     */
    Organization findOne(Long id);

    /**
     * 根据条件查询list
     * @param example
     * @return
     */
    List<Organization> findAll(OrganizationExample example);

}
