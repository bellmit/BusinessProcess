package com.mrbeard.process.blocks.authority.dto;

/**
 * @Author 胡彬
 * @Date 2018/11/6 16:12
 **/
public class LoginDto {
    private String username;
    private String password;
    /**
     * 验证码
     */
    private String randomCode;
    /**
     * 浏览器标识码
     */
    private String browerToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public String getBrowerToken() {
        return browerToken;
    }

    public void setBrowerToken(String browerToken) {
        this.browerToken = browerToken;
    }
}
