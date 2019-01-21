package com.mrbeard.process.blocks.publish.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mrbeard.process.blocks.authority.model.User;
import com.mrbeard.process.blocks.authority.service.PermissionService;
import com.mrbeard.process.blocks.authority.service.RoleService;
import com.mrbeard.process.blocks.authority.service.UserService;
import com.mrbeard.process.blocks.publish.dto.PostBlogDto;
import com.mrbeard.process.blocks.publish.mapper.BlogMapper;
import com.mrbeard.process.blocks.publish.model.Blog;
import com.mrbeard.process.blocks.publish.service.BlogService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.SessionUtil;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author 胡彬
 * @Date 2018/12/4 15:40
 **/
@Service
public class BlogServiceImpl implements BlogService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    BlogMapper blogDao;
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;
    @Autowired
    PermissionService permissionService;
    /**
     * 删除常量
     */
    private final String DELETE = "delete";
    /**
     * 修改常量
     */
    private final String UPDATE = "update";

    /**
     * 增添博文
     *
     * @param postBlogDto
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Result addBlog(PostBlogDto postBlogDto) throws ProcessRuntimeException {
        //获取用户信息
        User userInfo = SessionUtil.getUserInfo();
        logger.info("用户信息：==>" + JSON.toJSONString(userInfo));
        //判断是否存在
        if (userInfo == null) {
            return ResultGenerator.getNotLoginResult();
        }
        //设置博文实体信息
        Blog blog = new Blog(UUIDUtil.getUUID(), postBlogDto.getTitle(), postBlogDto.getContent(), userInfo.getUid(),new Date(), new Date());
        //新增博文
        try {
            blogDao.insert(blog);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return ResultGenerator.getErrorResult(e.getMessage());
        }
        return ResultGenerator.getSuccessResult("发布成功！");
    }

    /**
     * 获取博文列表
     *
     * @param pageSize
     * @param pageNumber
     * @return
     * @throws ProcessRuntimeException
     */
    @Override
    public Result getBlogList(Integer pageSize, Integer pageNumber) throws ProcessRuntimeException {
        //获取用户，查看是否正确
        User userInfo = SessionUtil.getUserInfo();
        if (userInfo == null) {
            return ResultGenerator.getNotLoginResult();
        }
        logger.info("分页数据：==>pageNum:"+pageNumber+"pageSize:"+pageSize);
        //设置分页数据
        PageHelper.startPage(pageNumber, pageSize);
        //查找数据
        List<Blog> blogList = null;
        try {
            blogList = blogDao.getBlogList();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            return ResultGenerator.getErrorResult(e.getMessage());
        }
        //返回数据
        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogList);
        return ResultGenerator.getSuccessResult(blogPageInfo);
    }

    /**
     * 修改、删除博文
     *
     * @param postBlogDto
     * @return
     */
    @Override
    public Result postBlog(PostBlogDto postBlogDto) {
        //获取用户信息
        User userInfo = SessionUtil.getUserInfo();
        //判断是否存在
        if (userInfo == null) {
            return ResultGenerator.getNotLoginResult();
        }
        logger.info("修改、删除：信息==>"+JSON.toJSONString(postBlogDto));
        //判断是否拥有删除、修改权限
        Set<String> perms = permissionService.getPermsByUserId(userInfo.getUid());
        if (!perms.contains(DELETE) || !perms.contains(UPDATE)) {
            return ResultGenerator.getErrorResult("权限不足！");
        }
        //删除
        if (ToolUtil.isEmpty(postBlogDto.getContent())) {
            try {
                blogDao.deleteByPrimaryKey(postBlogDto.getId());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(), e);
                return ResultGenerator.getErrorResult(e.getMessage());
            }
            return ResultGenerator.getSuccessResult("删除成功！");
        } else {
            //更新
            //判断数据是否存在
            if (ToolUtil.checkParamter(postBlogDto.getTitle(), postBlogDto.getContent()) != true){
                return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
            }
            //设置数据
            Blog blog = new Blog(postBlogDto.getId(), postBlogDto.getTitle(),postBlogDto.getContent(),null,null,new Date());
            try {
                blogDao.updateByPrimaryKey(blog);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(e.getMessage(),e);
                return ResultGenerator.getErrorResult(e.getMessage());
            }
            return ResultGenerator.getSuccessResult("更新成功！");
        }
    }
}
