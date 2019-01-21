package com.mrbeard.process.blocks.publish.dto;

/**
 * @Author 胡彬
 * @Date 2018/12/4 15:15
 * 发布博文Dto
 **/
public class PostBlogDto {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * id
     */
    private String id;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
