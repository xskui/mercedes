package com.redemption.shawshank.web.service;

import com.redemption.shawshank.Constants;
import com.redemption.shawshank.dao.OrganizationMapper;
import com.redemption.shawshank.pojo.Organization;
import com.redemption.shawshank.pojo.OrganizationExample;
import com.redemption.shawshank.utils.commonUtils.DateUtil;
import com.redemption.shawshank.utils.enums.ServerCodeEnum;
import com.redemption.shawshank.web.base.ResponBean;
import com.redemption.shawshank.web.service.inter.OrganizationService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author : xingshukui .
 * Date : 2017/7/7.
 * Desc :
 */@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

    private static final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);


    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public ResponBean create(Organization organization) {
        if (organization == null){
            log.info("组织新增接口参数为空");
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        if (StringUtils.isEmpty(organization.getName())){
            log.info("组织新增接口组织名称不能为空");
            return ResponBean.respon(ServerCodeEnum.PARM_ELE_NULL.code(), "组织名称不能为空");
        }

        log.info("请求参数：" + Constants.gson.toJson(organization));
        try{
            int res = organizationMapper.insert(organization);
            if (res <= 0){
                return ResponBean.ServerResponBean(ServerCodeEnum.DB_OPER_ERROR);
            }
            return ResponBean.successRespon();
        }catch (Exception e){
            log.error("组织新增接口异常 ：" + e.getMessage());
            return ResponBean.errorResponBean();
        }
    }

    @Override
    public ResponBean update(Organization organization) {
        if (organization == null){
            log.info("组织更新接口参数为空");
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        if (organization.getId() == null){
            log.info("组织更新接口参数中id为空");
            return ResponBean.respon(ServerCodeEnum.PARM_ELE_NULL.code(),"参数中ID为空");
        }
        if (StringUtils.isEmpty(organization.getName())){
            log.info("组织新增接口中组织名称不能为空");
            return ResponBean.respon(ServerCodeEnum.PARM_ELE_NULL.code(),"参数中组织名称为空");
        }
        try{
            int res = organizationMapper.updateByPrimaryKey(organization);
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
    public ResponBean remove(Long id) {
        if (id == null){
            return ResponBean.ServerResponBean(ServerCodeEnum.PARM_NULL);
        }
        Organization organization = new Organization();
        organization.setId(id);
        organization.setAvailable((short)0);
        organization.setUpdateTime(DateUtil.getCurrentDateTime());
        try{
            int res = organizationMapper.updateByPrimaryKeySelective(organization);
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
    public Organization findOne(Long id) {
        //这里不校验参数，在controller层校验
        return organizationMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Organization> findAll(OrganizationExample example) {
        //这里不校验参数，在controller层校验
        return organizationMapper.selectByExample(example);
    }
}
