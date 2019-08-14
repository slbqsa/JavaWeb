package com.onlineshoppingmall.service.impl;

import com.onlineshoppingmall.dao.PasswdDoMapper;
import com.onlineshoppingmall.dao.UserDoMapper;
import com.onlineshoppingmall.dataobject.PasswdDo;
import com.onlineshoppingmall.dataobject.UserDo;
import com.onlineshoppingmall.service.UserService;
import com.onlineshoppingmall.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDoMapper userDoMapper;
    @Autowired
    private PasswdDoMapper passwdDoMapper;

    @Override
    public UserModel getUserById(Integer id) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        if(userDo == null){
            return null;
        }
        PasswdDo passwdDo = passwdDoMapper.selectByUserId(userDo.getId());
        return ConvertFromDO(userDo,passwdDo);
    }

    private UserModel ConvertFromDO(UserDo userDo, PasswdDo passwdDo){
       if(userDo == null){
           return null;
       }
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userDo,userModel);
        if(passwdDo != null) {
            userModel.setEncrptPasswd(passwdDo.getEncrptPasswd());
        }
            return userModel;
        }
}
