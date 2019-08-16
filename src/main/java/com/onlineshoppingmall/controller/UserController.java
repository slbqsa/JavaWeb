package com.onlineshoppingmall.controller;

import com.onlineshoppingmall.controller.Viewer.UserViewer;
import com.onlineshoppingmall.error.EmError;
import com.onlineshoppingmall.error.IllegalException;
import com.onlineshoppingmall.reply.CommonReply;
import com.onlineshoppingmall.service.UserService;
import com.onlineshoppingmall.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller("user")
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
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
