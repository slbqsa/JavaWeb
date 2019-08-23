package com.onlineshoppingmall.controller;

import com.alibaba.druid.util.StringUtils;
import com.onlineshoppingmall.controller.Viewer.UserViewer;
import com.onlineshoppingmall.error.EmError;
import com.onlineshoppingmall.error.IllegalException;
import com.onlineshoppingmall.reply.CommonReply;
import com.onlineshoppingmall.service.UserService;
import com.onlineshoppingmall.service.model.UserModel;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

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
                                @RequestParam(name = "gender")Byte gender,
                                @RequestParam(name = "age")Integer age,
                                @RequestParam(name = "encrptPasswd")String encrptPasswd
                                )throws IllegalException{
       String SessionOtp = (String) this.httpServletRequest.getSession().getAttribute(phonenum);
       if(!com.alibaba.druid.util.StringUtils.equals(otpCode,SessionOtp)){
           throw new IllegalException(EmError.ILLEGAL_VALUED,"短信验证码不存在");
       }
       UserModel userModel = new UserModel();
       userModel.setName(name);
       userModel.setGender(gender);
       userModel.setAge(age);
       userModel.setPhonenum(phonenum);
       userModel.setMeans("byphone");
       userModel.setEncrptPasswd(MD5Encoder.encode(encrptPasswd.getBytes()));
       userService.register(userModel);
        return CommonReply.create(null);
    }


    @RequestMapping(value = "getotpnum",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
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
