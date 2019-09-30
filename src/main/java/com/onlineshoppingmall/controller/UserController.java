package com.onlineshoppingmall.controller;

import com.onlineshoppingmall.controller.Viewer.UserViewer;
import com.onlineshoppingmall.error.EmError;
import com.onlineshoppingmall.error.IllegalException;
import com.onlineshoppingmall.reply.CommonReply;
import com.onlineshoppingmall.service.UserService;
import com.onlineshoppingmall.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
//解决跨域问题的注解
@CrossOrigin
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;
/**
 * 新用户登录
 */
@RequestMapping(value = "/login", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
@ResponseBody
public CommonReply login(@RequestParam(name="phonenum")String phonenum,
                              @RequestParam(name="encrptPasswd")String encrptPasswd) throws IllegalException, UnsupportedEncodingException, NoSuchAlgorithmException {
    //参数校验
    if(org.apache.commons.lang3.StringUtils.isEmpty(phonenum)||
            org.apache.commons.lang3.StringUtils.isEmpty(encrptPasswd)){
        throw new IllegalException(EmError.ILLEGAL_VALUED);
    }
    UserModel userModel = userService.validateLogin(phonenum, encrptPasswd);
    this.httpServletRequest.getSession().setAttribute("LOGINED",true);
    this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);
return CommonReply.create(null);
    //用户登陆服务，用来校验用户登陆是否合法
   /*UserModel userModel =userService.validateLogin(phonenum,this.MD5Encoder(encrptPasswd));

    //将登陆凭证加入到用户登陆成功的session中
    UserViewer userViewer = ConvertFromModel(userModel);
    this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
    this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);

    System.out.println(this.httpServletRequest.getSession().getAttribute("IS_LOGIN"));

    return CommonReply.create(null);
*/
}

    /**
     * 新用户注册
     * @param phonenum
     * @return
     */
    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReply register(@RequestParam(name = "phonenum")String phonenum,
                                @RequestParam(name = "otpCode")String otpCode,
                                @RequestParam(name = "name")String name,
                                @RequestParam(name = "gender")Integer gender,
                                @RequestParam(name = "age")Integer age,
                                @RequestParam(name = "encrptPasswd")String encrptPasswd
                                )throws IllegalException{
       String SessionOtp = (String) this.httpServletRequest.getSession().getAttribute(phonenum);
       if(!com.alibaba.druid.util.StringUtils.equals(otpCode,SessionOtp)){
           throw new IllegalException(EmError.ILLEGAL_VALUED,"短信验证码不正确");
       }

       UserModel userModel = new UserModel();
       userModel.setName(name);
       userModel.setGender(new Byte(String.valueOf(gender.intValue())));
       userModel.setAge(age);
       userModel.setPhonenum(phonenum);
       userModel.setMeans("byphone");
       userModel.setEncrptPasswd(MD5Encoder.encode(encrptPasswd.getBytes()));
       userService.register(userModel);
        return CommonReply.create(null);
    }
/*
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定算法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
        return newstr;
    }
    */
    @RequestMapping(value = "getotp",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReply getOtpnum(@RequestParam(name = "phonenum")String phonenum){
        /**
         *随机数生成验证码
         */
        Random random = new Random();
        int randomInt =random.nextInt(1000000);
        randomInt += 100000;
        String optMsg = String.valueOf(randomInt);
        /**
         * 关联用户生成的验证码和手机号
         */
        httpServletRequest.getSession().setAttribute(phonenum,optMsg);
        System.out.println("phonenum="+phonenum+"&otpMsg="+optMsg);
   return CommonReply.create(null);
    }
    @RequestMapping("/get")
    //一个注解要了我的命
    @ResponseBody
    public CommonReply getUser(@RequestParam(name = "id")Integer id) throws IllegalException {
        UserModel userModel = userService.getUserById(id);
        if (userModel==null){
            throw  new IllegalException(EmError.USER_NOT_EXIST);
        }
        UserViewer userViewer = ConvertFromModel(userModel);
        return CommonReply.create(userViewer);
    }
    private  UserViewer ConvertFromModel(UserModel userModel){
        if(userModel == null){
            userModel.setEncrptPasswd("123");
        }
        UserViewer userViewer = new UserViewer();
        BeanUtils.copyProperties(userModel,userViewer);
        return userViewer;
    }
}
