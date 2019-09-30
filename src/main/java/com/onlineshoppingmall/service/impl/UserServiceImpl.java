package com.onlineshoppingmall.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.onlineshoppingmall.dao.PasswdDoMapper;
import com.onlineshoppingmall.dao.UserDoMapper;
import com.onlineshoppingmall.dataobject.PasswdDo;
import com.onlineshoppingmall.dataobject.UserDo;
import com.onlineshoppingmall.error.EmError;
import com.onlineshoppingmall.error.IllegalException;
import com.onlineshoppingmall.service.UserService;
import com.onlineshoppingmall.service.model.UserModel;
import com.onlineshoppingmall.validator.ValidationResult;
import com.onlineshoppingmall.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validation;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDoMapper userDoMapper;
    @Autowired
    private PasswdDoMapper passwdDoMapper;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public UserModel getUserById(Integer id) {
        UserDo userDo = userDoMapper.selectByPrimaryKey(id);
        if(userDo == null){
            return null;
        }
        PasswdDo passwdDo = passwdDoMapper.selectByUserId(userDo.getId());
        return ConvertFromDO(userDo,passwdDo);
    }
    @Transactional
    @Override
    public void register(UserModel userModel) throws IllegalException{
        if(userModel==null){
            throw new IllegalException(EmError.ILLEGAL_VALUED);
        }
//        if(StringUtils.isEmpty(userModel.getName() )
//                || userModel.getGender()==null
//                || userModel.getAge()==null
//                || StringUtils.isEmpty(userModel.getPhonenum())){
//            throw new IllegalException(EmError.ILLEGAL_VALUED);
//        }

        ValidationResult result = validator.validate(userModel);
        if(result.isHasErrors()){
            throw new IllegalException(EmError.ILLEGAL_VALUED,result.getErrMsg());
        }
/**
 *         model->dataobject
 *         convertFromModel = convertToDo
 */
UserDo userDo = convertToDo(userModel);{
    userDoMapper.insertSelective(userDo);
    PasswdDo passwdDo = convertToPasswd(userModel);
    passwdDoMapper.insertSelective(passwdDo);
    return;
        }
    }
    @Override
    public UserModel validateLogin(String phonenum,String encrptPasswd)throws IllegalException{
        //通过手机获取用户信息
        UserDo userDo = userDoMapper.selectByTelphone(phonenum);
        if(userDo == null){
            throw new IllegalException(EmError.USER_LOGIN_FAIL);
        }
        PasswdDo passwdDo  = passwdDoMapper.selectByUserId(userDo.getId());
        UserModel userModel = ConvertFromDO(userDo,passwdDo);


        //比对用户信息内加密的密码是否和传入的密码相匹配
        if(!StringUtils.equals(encrptPasswd,userModel.getEncrptPasswd())){
            throw new IllegalException(EmError.USER_LOGIN_FAIL);
        }
return userModel;
    }
    private PasswdDo convertToPasswd(UserModel userModel){
        if (userModel==null){
            return null;
        }
        PasswdDo passwdDo = new PasswdDo();
        passwdDo.setEncrptPasswd(userModel.getEncrptPasswd());
        passwdDo.setUserId(userModel.getId());
        return passwdDo;
    }

    private UserDo convertToDo(UserModel userModel){
        if (userModel==null){
            return null;
        }
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userModel,userDo);
        return userDo;
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
