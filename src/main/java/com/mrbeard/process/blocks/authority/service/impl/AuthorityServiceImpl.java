package com.mrbeard.process.blocks.authority.service.impl;

import com.alibaba.fastjson.JSON;
import com.mrbeard.process.blocks.authority.dto.LoginDto;
import com.mrbeard.process.blocks.authority.dto.LoginResponseDto;
import com.mrbeard.process.blocks.config.model.User;
import com.mrbeard.process.blocks.authority.service.AuthorityService;
import com.mrbeard.process.blocks.authority.service.PermissionService;
import com.mrbeard.process.blocks.authority.service.RoleService;
import com.mrbeard.process.blocks.authority.service.UserService;
import com.mrbeard.process.common.Constant;
import com.mrbeard.process.exception.ProcessRuntimeException;
import com.mrbeard.process.result.Result;
import com.mrbeard.process.result.ResultGenerator;
import com.mrbeard.process.util.JedisUtil;
import com.mrbeard.process.util.ToolUtil;
import com.mrbeard.process.util.UUIDUtil;
import com.mrbeard.process.util.WebUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;


/**
 * @Author 胡彬
 * @Date 2018/10/31 15:28
 **/
@Service
public class AuthorityServiceImpl implements AuthorityService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;


    /**
     * 图片宽度
     */
    private static int WIDTH = 200;
    /**
     * 图片长度
     */
    private static int HEIGHT = 50;
    /**
     * 登录常量
     */
    private static String UNDEFINDBROWERTOKEN = "undefinedBrowerToken";

    /**
     * 正则校验密码
     */
    private static Pattern PSASSWORD_PATTERN = Pattern.compile("[0-9a-zA-Z_]{6,20}");
    /**
     * 正则校验用户名
     */
    private static Pattern USERNAME_PATTERN = Pattern.compile("[0-9a-zA-Z_]{3,10}");
    /**
     * 正则校验昵称
     */
    private static Pattern NICK_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5_a-zA-Z0-9_#]{4,10}");

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    @Override
    public Result login(LoginDto loginDto) throws ProcessRuntimeException {
        if(ToolUtil.isEmpty(loginDto.getBrowerToken())){
            return ResultGenerator.getErrorResult("浏览器标识码不存在！");
        }else if(ToolUtil.isEmpty(loginDto.getRandomCode())){
            return ResultGenerator.getErrorResult("请输入验证码！");
        }
        logger.info("登录信息：loginDto ==>"+JSON.toJSONString(loginDto));
        //先判断browerToken是否存在或已经失效
        String vCode = JedisUtil.get("browerToken_" + loginDto.getBrowerToken());
        //验证码已经失效
        if (ToolUtil.isEmpty(vCode)) {
           return ResultGenerator.getErrorResult(Constant.RANDOMCODE_TIMEOUT);
        }
        //验证码错误
        if(UNDEFINDBROWERTOKEN.equals(vCode) || loginDto.getRandomCode().toUpperCase().equals(vCode.toUpperCase()) != true){
            return ResultGenerator.getErrorResult(Constant.RANDOMCODE_ERROR);
        }
        //查询用户是否存在
        loginDto.setPassword(ToolUtil.Md5(loginDto.getPassword()));
        List<User> userList = userService.selectUserByName(loginDto.getUsername());
        //未找到用户
        if(CollectionUtils.isEmpty(userList)){
            return ResultGenerator.getErrorResult(Constant.USER_NOT_EXIST);
        }
        if(userList.size() > 1){
            throw new ProcessRuntimeException("找到多个用户，请确认信息是否有误！");
        }
        User user = userList.get(0);
        //密码不正确
        if(loginDto.getPassword().equals(user.getPassword()) != true){
            return ResultGenerator.getErrorResult(Constant.ERROR_PASSWORD);
        }
        //用户被禁用
        if(user.getState() == Constant.STATE_CLOSE){
            return ResultGenerator.getErrorResult(Constant.USER_DISABLED);
        }
        //获取到ip、端口设置到当前用户
        HttpServletRequest request = WebUtil.getRequest();
        user.setLoginIp(request.getRemoteAddr());
        user.setLoginPort(request.getRemotePort());
        //更新ip、端口
        userService.updateById(user);
        //获取到用户角色权限
        user.setRole(roleService.getRoleByUserId(user.getUid()));
        user.setPermissions(permissionService.getPermsByUserId(user.getUid()));
        //将用户信息放入userInfo
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("userInfo",user);
        //将用户信息放入redis
        String usertoken = UUIDUtil.getUUID();
        JedisUtil.add("usertoken_"+usertoken, JSON.toJSONString(userInfoMap),Constant.USER_TOKEN_INVALIDTIME);
        //登录成功，移除临时码
        JedisUtil.remove("browerToken_"+loginDto.getBrowerToken());
        //封装返回前端信息
        LoginResponseDto loginResponseDto = new LoginResponseDto(usertoken,user.getUname(),user.getUid(),user.getNick(),user.getRole());
        logger.info("登录返回信息：==>loginResponseDto"+JSON.toJSONString(loginResponseDto));
        return ResultGenerator.getSuccessResult(loginResponseDto);
    }



    /**
     * 用户注销
     * @return
     */
    @Override
    public Result logout(String usertoken) throws ProcessRuntimeException {
        if (ToolUtil.isEmpty(usertoken)) {
            throw new ProcessRuntimeException(Constant.PARAM_LOSS);
        }
        JedisUtil.remove("usertoken_" + usertoken);
        return ResultGenerator.getSuccessResult("注销成功");
    }

    /**
     * 根据browerToken获取验证码
     * @param browerToken
     */
    @Override
    public void getRandomCode(String browerToken) throws IOException {
        if (ToolUtil.isEmpty(browerToken)) {
            throw new ProcessRuntimeException(Constant.PARAM_LOSS);
        }
        if (ToolUtil.isEmpty(JedisUtil.get("browerToken_" + browerToken))) {
            throw new ProcessRuntimeException(Constant.ERROR_BROWERTOKEN);
        }

        HttpServletResponse response = WebUtil.getResponse();
        ByteArrayOutputStream bos = null;
        ServletOutputStream sos = null;
        try {
            //使用jwt绘制验证码
            response.setContentType("image/jpeg");
            sos = response.getOutputStream();
            response.setHeader("pragma", "no-cache");
            response.setHeader("cache-control", "no-cache");
            response.setDateHeader("Expires", 0);
            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
            Graphics g = image.getGraphics();
            //生成随机码
            char[] rands = generateCheckCode();
            //绘制背景
            drawBackground(g);
            //绘制字体
            drawRands(g, rands);
            g.dispose();
            bos = new ByteArrayOutputStream();
            ImageIO.write(image, "JPEG", bos);
            byte[] buf = bos.toByteArray();

            response.setContentLength(buf.length);
            sos.write(buf);
            JedisUtil.add("browerToken_" + browerToken, new String(rands), Constant.BORWER_TOKEN_INVALIDTIME);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw new ProcessRuntimeException(e.getMessage());
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (sos != null) {
                sos.close();
            }
        }
    }

    /**
     * 检测是否过期
     * @return
     */
    @Override
    public Result isTokenTimeOut() {
        return ResultGenerator.getSuccessResult("success");
    }

    /**
     * 绘制字体
     * @param g
     * @param rands
     */
    private void drawRands(Graphics g, char[] rands) {
        Random random = new Random();
        int red = random.nextInt(110);
        int green = random.nextInt(50);
        int blue = random.nextInt(50);
        g.setColor(new Color(red, green, blue));
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 30));
        g.drawString("" + rands[0], 17, 37);
        g.drawString("" + rands[1], 56, 42);
        g.drawString("" + rands[2], 100, 49);
        g.drawString("" + rands[3], 150, 39);
    }

    /**
     * 绘制背景
     * @param g
     */
    private void drawBackground(Graphics g) {
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
    }

    /**
     * 生成四位的随机验证码
     * @return
     */
    private char[] generateCheckCode() {
        String chars = "3456789ABCDEFGHJKLMNPQRSTUVWXY";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * 30);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }
}
