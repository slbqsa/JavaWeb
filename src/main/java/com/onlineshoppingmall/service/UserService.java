package com.onlineshoppingmall.service;

import com.onlineshoppingmall.error.IllegalException;
import com.onlineshoppingmall.service.model.UserModel;

public interface UserService {
UserModel getUserById(Integer id);
void register(UserModel userModel) throws IllegalException;

UserModel validateLogin(String phonenum,String encrptPassword) throws IllegalException;
}
