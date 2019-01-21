package com.mrbeard.process.blocks.publish.service;


import com.mrbeard.process.blocks.publish.dto.PostBlogDto;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;

/**
 * @Author 胡彬
 * @Date 2018/12/4 15:30
 **/
public interface BlogService {


    /**
     * 增删改博文
     * @param postBlogDto
     * @return
     * @throws ProcessRuntimeException
     */
    Result addBlog(PostBlogDto postBlogDto) throws ProcessRuntimeException;

    /**
     * 获取博文列表
     * @param pageSize
     * @param pageNumber
     * @return
     * @throws ProcessRuntimeException
     */
    Result getBlogList(Integer pageSize, Integer pageNumber) throws ProcessRuntimeException;

    /**
     * 修改、删除博文
     * @param postBlogDto
     * @return
     */
    Result postBlog(PostBlogDto postBlogDto);
}
