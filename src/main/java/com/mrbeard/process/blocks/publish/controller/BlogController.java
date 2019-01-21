package com.mrbeard.process.blocks.publish.controller;


import com.mrbeard.process.blocks.publish.dto.PostBlogDto;
import com.mrbeard.process.blocks.publish.service.BlogService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.ToolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 胡彬
 * @Date 2018/12/4 14:41
 * 发布博文
 **/
@RestController
@RequestMapping("/api")
public class BlogController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    BlogService blogService;

    /**
     * 添加博文
     * @param postBlogDto
     * @return
     */
    @RequestMapping(value = "/addBlog", method = RequestMethod.POST)
    public Result addBlog(PostBlogDto postBlogDto) {
        logger.info("=======================/api/addBlog");
        if (ToolUtil.checkParamter(postBlogDto.getTitle(), postBlogDto.getContent()) != true) {
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return blogService.addBlog(postBlogDto);
    }

    /**
     * 修改、删除博文
     * @param postBlogDto
     * @return
     */
    @RequestMapping(value = "/postBlog",method = RequestMethod.POST)
    public Result postBlog(PostBlogDto postBlogDto){
        logger.info("=======================/api/postBlog");
        if(ToolUtil.isEmpty(postBlogDto.getId())){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return blogService.postBlog(postBlogDto);
    }


    /**
     * 获取博文列表，按时间倒序
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/getBlogList",method = RequestMethod.GET)
    public Result getBlogList(Integer pageSize,Integer pageNumber){
        logger.info("=======================/api/getBlogList");
        if(ToolUtil.checkParamter(pageNumber,pageSize) != true){
            return ResultGenerator.getErrorResult(Constant.PARAM_LOSS);
        }
        return blogService.getBlogList(pageSize,pageNumber);
    }

}
