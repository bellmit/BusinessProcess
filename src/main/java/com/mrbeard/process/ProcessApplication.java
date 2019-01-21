package com.mrbeard.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author 胡彬
 * @Date 2018/11/26 15:01
 */
@SpringBootApplication
@EnableScheduling
public class ProcessApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ProcessApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 继承SpringBootServletInitializer ，
     * 覆盖configure()，把启动类Application注册进去。
     * 外部web应用服务器构建Web Application Context的时候，会把启动类添加进去。
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ProcessApplication.class);
    }

}

