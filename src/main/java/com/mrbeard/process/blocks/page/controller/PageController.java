package com.mrbeard.process.blocks.page.controller;

import com.mrbeard.process.util.ToolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author 胡彬
 * @Date 2018/11/9 9:47
 **/
@Controller
public class PageController {

    @GetMapping("/login")
    public String login(){
        return "redirect:/static/login.html";
    }

    @GetMapping("/errorPage")
    public String error(){
        return "redirect:/static/login.html";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/user-manager")
    public String userManager(){
        return "user-manager";
    }

    @GetMapping("/role-manager")
    public String roleManager(){
        return "role-manager";
    }


}
