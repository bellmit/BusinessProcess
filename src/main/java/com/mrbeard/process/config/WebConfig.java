package com.mrbeard.process.config;

import com.mrbeard.process.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @author 胡彬
 * @date 2018/11/26
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT","OPTIONS")
                .maxAge(3600);
    }

    /**
     * 将判断是否登陆的拦截器添加到配置中
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/getBrowerToken", "/api/login",
                        "/api/getRandomCode","/api/sessionError",
                        "/login","/errorPage","/static/**");
    }


    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    /**
     *默认视图配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addStatusController("/static/404.html",HttpStatus.NOT_FOUND);
//        registry.addStatusController("/static/nepadmin/views/exception/403.html",HttpStatus.FORBIDDEN);
//        registry.addStatusController("/static/nepadmin/views/exception/500.html",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
