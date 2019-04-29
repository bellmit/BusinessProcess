package com.mrbeard.process.blocks.config.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.authority.model.Role;
import com.mrbeard.process.blocks.authority.service.RoleService;
import com.mrbeard.process.blocks.config.dto.PostRoleDto;
import com.mrbeard.process.blocks.config.service.RoleConfigService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName RoleConfigServiceImpl
 * @Description TODO
 * @Author Mrbeard
 * @Date 2019/1/11 15:40
 * @Version 1.0
 **/
@Service
public class RoleConfigServiceImpl implements RoleConfigService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RoleService roleService;


    /**
     * 获取角色列表
     * @return
     */
    @Override
    public Result getRoleList(Integer pageNum,Integer pageSize,String rname) throws ProcessRuntimeException {
        PageHelper.startPage(pageNum,pageSize);
        Role role = new Role();
        role.setRname(rname);
        List<Role> roleList = roleService.getRoleList(role);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        return ResultGenerator.getSuccessResult(pageInfo);
    }


    /**
     * 配置角色
     *
     * @param role 角色信息
     * @return
     */
    @Override
    public Result postRole(PostRoleDto role) throws ProcessRuntimeException {
        /**
         *
         * 判断操作类型：新增、删除、修改
         */
        if(ToolUtil.isNotEmpty(role.getRid())){
            //所有数据都不存在
            if(ToolUtil.checkIfAllParamtersExit(role.getRname(),role.getRdescription()) != true){
                //删除
                roleService.deleteById(role.getRid());
                return ResultGenerator.getSuccessResult("删除成功！");
            }
            //修改
            if(ToolUtil.checkParamter(role.getRname(),role.getRdescription()) != true){
                return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
            }
            Role roleInData = roleService.selectById(role.getRid());
            roleInData.setUpdatedTime(new Date());
            roleInData.setRdescription(role.getRdescription());
            roleInData.setRname(role.getRname());
            roleService.updateById(roleInData);
            return ResultGenerator.getSuccessResult("更新成功！");
        }
        //新增
        if(ToolUtil.checkParamter(role.getRname(),role.getRdescription()) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        Role roleNew = new Role();
        roleNew.setRname(role.getRname());
        roleNew.setRdescription(role.getRdescription());
        String rid = UUIDUtil.getUUID();
        roleNew.setRid(rid);
        roleNew.setCreatedTime(new Date());
        roleNew.setUpdatedTime(new Date());
        roleService.insert(roleNew);
        return ResultGenerator.getSuccessResult("新增成功！");
    }


    /**
     * 获取对应角色信息
     * @param rid
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Result getRole(String rid) throws ProcessRuntimeException {
        Role role = roleService.getRole(rid);
        return ResultGenerator.getSuccessResult(role);
    }



}
