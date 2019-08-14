package com.onlineshoppingmall.controller;

import com.onlineshoppingmall.controller.Viewer.UserViewer;
import com.onlineshoppingmall.service.UserService;
import com.onlineshoppingmall.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("user")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/get")
    //一个注解要了我的命
    @ResponseBody
    public UserViewer getUser(@RequestParam(name = "id")Integer id){
        UserModel userModel = userService.getUserById(id);
        return ConvertFromModel(userModel);
    }
    private  UserViewer ConvertFromModel(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserViewer userViewer = new UserViewer();
        BeanUtils.copyProperties(userModel,userViewer);
        return userViewer;
    }
}
